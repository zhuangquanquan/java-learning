package cn.com.rivercloud.wechat.wx.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageEnum {

    text("text", "文本消息"),
    image("image", "图片消息"),
    location("location", "位置消息"),
    link("link", "链接消息"),
    video("video", "视频消息"),
    voice("voice", "语音消息");

    private String messageType;
    private String summary;
}
