package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.entity.user.User;
import cn.com.rivercloud.wechat.jwt.JwtUtils;
import cn.com.rivercloud.wechat.jwt.LogoutCache;
import cn.com.rivercloud.wechat.service.user.impl.UserServiceImpl;
import cn.hutool.core.map.MapUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        jwtUtils.addCookie(request, response, null);
        return Result.succ(null);
    }

    @PostMapping(value = "/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = userService.getOne(username);
        if (Objects.isNull(user) || Strings.isEmpty(user.getPassword()) || !user.getPassword().equals(password)) {
           return Result.fail("用户名或密码错误");
        }
        String token = jwtUtils.generateToken(UUID.randomUUID().toString(), user.getId());
        response.setHeader(jwtUtils.getHeader(), token);
        response.setHeader("Access-control-Expose-Headers", jwtUtils.getHeader());

        jwtUtils.addCookie(request, response, token);

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("userName", user.getUserName())
                .put("realName", user.getRealName())
                .put(jwtUtils.getHeader(), token)
                .map());
    }

}
