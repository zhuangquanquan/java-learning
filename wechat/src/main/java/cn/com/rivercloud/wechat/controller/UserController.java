package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.dto.LoginDto;
import cn.com.rivercloud.wechat.common.lang.JsonResponseBuilder;
import cn.com.rivercloud.wechat.pojo.User;
import cn.com.rivercloud.wechat.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public JSONObject hello(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        JsonResponseBuilder responseBuilder = new JsonResponseBuilder();
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = userService.validateAccount(username, password);
        return responseBuilder.data(user).build();
    }


    @GetMapping("/list")
    public JSONObject list() {
        JsonResponseBuilder responseBuilder = new JsonResponseBuilder();
        List<User> userList = userService.getUserList();
        return responseBuilder.success(true).data(userList).build();
    }
}
