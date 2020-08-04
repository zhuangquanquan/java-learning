package cn.com.rivercloud.wechat.jwt;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

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
        //添加cookie判断
        if (Strings.isEmpty(jwt) && request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            int length = cookies.length;
            for(int i = 0; i < length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        if(StringUtils.isEmpty(jwt)) {
            return true;
        }
        try {
            // 校验jwt
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration()) || LogoutCache.me().isLogout(claim.getId())) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.setContentType("application/json;charset=utf-8");
                httpResponse.getWriter().print(JSONUtil.toJsonStr(Result.fail(401,"token已失效，请重新登录",null)));
                Cookie cookie = new Cookie(jwtUtils.getHeader(), null);
                cookie.setPath(request.getContextPath() + "/");
                cookie.setHttpOnly(true);
                httpResponse.addCookie(cookie);
                return false;
            }
            return executeLogin(request, servletResponse);
        } catch (Exception e) {
            log.error("认证异常",e);
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType("application/json;charset=utf-8");
            try {
                httpResponse.getWriter().print(JSONUtil.toJsonStr(Result.fail(401,"认证异常",null)));
            } catch (IOException ex) {
            }
            return false;
        }
    }

    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(jwtUtils.getHeader());

        //添加cookie判断
        if (Strings.isEmpty(token) && httpServletRequest.getCookies() != null) {
            Cookie[] cookies = httpServletRequest.getCookies();
            int length = cookies.length;
            for(int i = 0; i < length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
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

}
