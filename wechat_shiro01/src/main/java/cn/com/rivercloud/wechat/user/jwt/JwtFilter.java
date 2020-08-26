package cn.com.rivercloud.wechat.user.jwt;

import cn.com.rivercloud.wechat.user.common.constant.TokenDateUnitConstant;
import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.config.SpringContextUtil;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
        if (jwtUtils == null) {
            jwtUtils = (JwtUtils) SpringContextUtil.getBean("jwtUtils");
        }
        //判断请求的请求头是否带上 "Token"
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = jwtUtils.getToken(request);
        if(StringUtils.isEmpty(token)) {
            return true;
        }
        // 校验jwt
        Claims claim = jwtUtils.getClaimByToken(token);
        if(claim == null || jwtUtils.isTokenExpired(claim.getExpiration()) || LogoutCache.me().isLogout(claim.getId())) {
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().print(JSONUtil.toJsonStr(Result.fail(401,"token已失效，请重新登录",null)));
            } catch (IOException e) {
                log.error("token失效", e);
                e.printStackTrace();
            }
            //jwtUtils.addCookie(request, response, null);
            return false;
        }
        //如果剩余时间小于系统过期时间的一半，则重新生成token
        if((claim.getExpiration().getTime() - System.currentTimeMillis()) < jwtUtils.getExpire()*TokenDateUnitConstant.UNIT*0.5){
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            long userId = Long.valueOf(JSONUtil.toJsonStr(claim.getSubject()));
            token = jwtUtils.generateToken(UUID.randomUUID().toString(), userId);
            response.setHeader(jwtUtils.getHeader(), token);
            response.setHeader("Access-control-Expose-Headers", jwtUtils.getHeader());
            jwtUtils.addCookie(request, response, token);
        }

        return executeLogin(request, servletResponse);
    }

    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        if (jwtUtils == null) {
            jwtUtils = (JwtUtils) SpringContextUtil.getBean("jwtUtils");
        }
        String token = jwtUtils.getToken((HttpServletRequest) request);
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
