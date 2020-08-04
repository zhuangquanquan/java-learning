package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//客户经理
@RestController
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/getMessage")
    public Result getMessage() {
        return Result.succ("您拥有【客户经理】权限，可以获得该接口的信息");
    }
}
