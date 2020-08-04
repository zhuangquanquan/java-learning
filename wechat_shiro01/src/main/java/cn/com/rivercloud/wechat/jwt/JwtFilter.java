package cn.com.rivercloud.wechat.jwt;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        //判断请求的请求头是否带上 "Token"
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            try {
                // 校验jwt
                Claims claim = jwtUtils.getClaimByToken(jwt);
                if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration()) || LogoutCache.me().isLogout(claim.getId())) {
                    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                    httpResponse.setContentType("application/json;charset=utf-8");
                    httpResponse.getWriter().print(JSONUtil.toJsonStr(Result.fail(401,"token已失效，请重新登录",null)));
                    return false;
                }
                return executeLogin(request, servletResponse);
            } catch (Exception e) {
                logger.error("",e);
                //token 错误
                return false;
            }
        }
    }

    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(jwtUtils.getHeader());
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private boolean responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/" + message);
            return false;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
