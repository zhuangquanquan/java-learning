package cn.com.rivercloud.wechat.wx.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.config.WeChatConfig;
import cn.com.rivercloud.wechat.wx.service.WeChatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatAuthController {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    WeChatAuthService weChatAuthService;

    @GetMapping("/getRequestCodeUrl")
    public Result sign(HttpServletResponse response) {
        String str = null;
        try {
            str = weChatAuthService.getCode(response);
            return Result.succ(str);
        } catch (IOException e) {
            log.error("获取code失败", e);
            return Result.fail("获取code失败");
        }

    }

    @GetMapping("/auth")
    public Result auth(HttpServletRequest request,@Param("code") String code) {
        weChatAuthService.codeExchangeAccessToken(code);//通过这个code获取access_token
        return Result.succ("");
    }








}
