package cn.com.rivercloud.wechat.controller;

import cn.com.rivercloud.wechat.common.lang.JsonResponseBuilder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//管理员
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/getMessage")
    public JSONObject getMessage() {
        JsonResponseBuilder responseBuilder = new JsonResponseBuilder();
        return responseBuilder.success(true).message("您拥有【管理员】权限，可以获得该接口的信息").build();
    }
}
