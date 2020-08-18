package cn.com.rivercloud.wechat.wx.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UrlEnum {

    accessToken("accessToken", "获取token"),
    userList("userList", "获取用户列表"),
    templateList("templateList", "获取模板列表"),
    sendTemplateMessage("sendTemplateMessage", "发送模板消息"),
    code("code", "用户同意授权，获取code"),
    codeExchangeAccessToken("codeExchangeAccessToken", "通过code换取网页授权access_token");

    private String methodName;
    private String summary;

}
