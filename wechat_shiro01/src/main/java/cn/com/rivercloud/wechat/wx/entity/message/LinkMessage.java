package cn.com.rivercloud.wechat.wx.entity.message;

import lombok.Data;

@Data
public class LinkMessage extends BaseMessage {

    /**
     * 消息標題
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息鏈接
     */
    private String Url;
}
