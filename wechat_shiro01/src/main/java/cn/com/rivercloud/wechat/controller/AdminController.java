package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.jwt.Auths;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//管理员
@RequiresRoles("admin")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    Auths auths;

    @GetMapping("/getMessage")
    public Result getMessage(HttpServletRequest request) {
        System.out.println("用户ID==>" + auths.getRequestUserId(request));
        return Result.succ("您拥有【管理员】权限，可以获得该接口的信息");
    }
}
