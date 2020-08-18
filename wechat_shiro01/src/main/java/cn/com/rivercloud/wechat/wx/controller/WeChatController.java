package cn.com.rivercloud.wechat.wx.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.config.WeChatConfig;
import cn.com.rivercloud.wechat.wx.common.Signature;
import cn.com.rivercloud.wechat.wx.common.dto.WeChatAuthDto;
import cn.com.rivercloud.wechat.wx.common.util.MessageUtil;
import cn.com.rivercloud.wechat.wx.entity.TemplateList;
import cn.com.rivercloud.wechat.wx.entity.message.TextMessage;
import cn.com.rivercloud.wechat.wx.service.WXService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    WXService wxService;


    @GetMapping("/sign")
    public void sign(@Validated WeChatAuthDto auth, HttpServletResponse response) {
        log.info("微信接入认证=>{}", auth.toString());
        boolean isOk = Signature.checkSignature(weChatConfig.getToken(), auth.getSignature(), auth.getTimestamp(), auth.getNonce());
        if (!isOk) {
            log.error("微信接入认证失败，auth=>{}",auth.toString());
            return;
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(auth.getEchostr());
            log.error("微信接入认证成功");
        } catch (IOException e) {
            log.error("微信接入认证异常", e);
        }
    }

    /*@GetMapping("/getAccessToken")
    public Result getAssessToken() {
       AssessToken rep = wxService.getToken();
       return Result.succ(MapUtil.builder().put("token", rep.getAccess_token()).put("expires", rep.getExpires_in()).map());
    }*/

    @PostMapping("/sign")
    public void signPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> paramsMap = MessageUtil.parseXml(request);
        System.out.println("paramsMap:" + paramsMap);
        String MsgType = paramsMap.get("MsgType");
        String ToUserName = paramsMap.get("ToUserName");
        String FromUserName = paramsMap.get("FromUserName");
        String MsgId = paramsMap.get("MsgId");
        long CreateTime = Long.valueOf(paramsMap.get("CreateTime"));
        String resMessage = "";
        if (paramsMap.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            String Content = paramsMap.get("Content");
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(MsgType).setFromUserName(ToUserName).setToUserName(FromUserName).setMsgId(MsgId).setCreateTime(CreateTime);
            textMessage.setContent("welcome " + Content);
            resMessage = MessageUtil.textMessageToXml(textMessage);
            System.out.println(resMessage);
        } else {
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(MsgType).setFromUserName(ToUserName).setToUserName(FromUserName).setMsgId(MsgId).setCreateTime(CreateTime);
            textMessage.setContent("the message type not support!!!");
            resMessage = MessageUtil.textMessageToXml(textMessage);
            System.out.println(resMessage);
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(resMessage);
        } catch (IOException e) {
            log.error("消息异常", e);
            e.printStackTrace();
        }
    }

    @GetMapping("/getUserList")
    public void getUserList() {
        wxService.getUserList();
    }

    @GetMapping("/getTemplateList")
    public Result getTemplateList() {
        TemplateList templateList = wxService.getTemplateList();
        if (Objects.isNull(templateList) || Objects.isNull(templateList.getTemplate_list()) || templateList.getTemplate_list().isEmpty()) {
            return Result.fail("模板列表为空，未获取到模板列表");
        }
        System.out.println("templateList:" + templateList);
        wxService.sendTemplate();
        return Result.succ("");
    }





}
