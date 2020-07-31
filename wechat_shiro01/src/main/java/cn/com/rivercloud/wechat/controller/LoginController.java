package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.JsonResponseBuilder;
import cn.com.rivercloud.wechat.service.impl.UserServiceImpl;
import cn.com.rivercloud.wechat.util.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    /*@RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public JSONObject notLogin() {
        return new JsonResponseBuilder().success(false).message("您尚未登陆!").build();
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public JSONObject notRole() {
        return new JsonResponseBuilder().success(false).message("您没有权限!").build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JSONObject logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return new JsonResponseBuilder().success(false).message("成功注销！").build();
    }
*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@Validated @RequestBody LoginDto loginDto) {
        JsonResponseBuilder responseBuilder = new JsonResponseBuilder();
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        String realPassword = userService.getPassword(username);
        if (realPassword == null) {
            return responseBuilder.success(false).message("用户名错误").build();
        } else if (!realPassword.equals(password)) {
            return responseBuilder.success(false).message("密码错误").build();
        } else {
            return responseBuilder.success(true).message(JWTUtil.createToken(username)).build();
        }
    }


    @RequestMapping(path = "/unauthorized/{message}")
    public JSONObject unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return new JsonResponseBuilder().success(false).message(message).build();
    }

}
