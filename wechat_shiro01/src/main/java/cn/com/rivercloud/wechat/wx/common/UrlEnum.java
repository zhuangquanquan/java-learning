package cn.com.rivercloud.wechat.wx.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UrlEnum {

    accessToken("accessToken", "获取token"),
    user("user", "获取用户基本信息"),
    userList("userList", "获取用户列表"),
    templateList("templateList", "获取模板列表"),
    templateId("templateId", "获得模板Id"),
    sendTemplateMessage("sendTemplateMessage", "发送模板消息");

    private String methodName;
    private String summary;

}
