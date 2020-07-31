package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.JsonResponseBuilder;
import cn.com.rivercloud.wechat.mapper.UserMapper;
import cn.com.rivercloud.wechat.pojo.User;
import cn.com.rivercloud.wechat.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@Validated @RequestBody LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        User user = userService.getByUserName(username);
        String role = user.getRole();
        if ("user".equals(role)) {
            return new JsonResponseBuilder().success(true).message("欢迎登陆").build();
        }
        if ("manage".equals(role)) {
            return new JsonResponseBuilder().success(true).message("欢迎登陆客户经理管理界面").build();
        }
        if ("admin".equals(role)) {
            return new JsonResponseBuilder().success(true).message("欢迎来到管理员页面").build();
        }
        return new JsonResponseBuilder().success(false).message("权限错误！").build();
    }

}
