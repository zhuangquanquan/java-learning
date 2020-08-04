package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//管理员
@RequiresRoles("admin")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/getMessage")
    public Result getMessage() {
        return Result.succ("您拥有【管理员】权限，可以获得该接口的信息");
    }
}
