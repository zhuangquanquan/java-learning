package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.jwt.JwtUtils;
import cn.com.rivercloud.wechat.jwt.LogoutCache;
import cn.com.rivercloud.wechat.pojo.User;
import cn.com.rivercloud.wechat.service.impl.UserServiceImpl;
import cn.hutool.core.map.MapUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtUtils jwtUtils;

    //@RequiresRoles(value={"admin","manage","user"},logical = Logical.OR)
    //@RequiresAuthentication
    @PostMapping(value = "/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        Claims claims = jwtUtils.getClaimByToken(subject.getPrincipal().toString()) ;
        LogoutCache.me().put(claims.getId(), claims.getExpiration().getTime());
        //注销
        Cookie cookie = new Cookie(jwtUtils.getHeader(), null);
        cookie.setPath(request.getContextPath() + "/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return Result.succ(null);
    }

    @PostMapping(value = "/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = userService.getByUserName(username);
        if (Objects.isNull(user) || Strings.isEmpty(user.getPassword()) || !user.getPassword().equals(password)) {
            return Result.fail("用户名或密码错误");
        }
        String jwt = jwtUtils.generateToken(UUID.randomUUID().toString(), user.getId());
        response.setHeader("token", jwt);
        response.setHeader("Access-control-Expose-Headers", "token");

        Cookie cookie = new Cookie(jwtUtils.getHeader(), jwt);
        cookie.setPath(request.getContextPath() + "/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("userName", user.getUserName())
                .put("realName", user.getRealName())
                .put("role", user.getRole())
                .put("token", jwt)
                .map());
    }



}
