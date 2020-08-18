package cn.com.rivercloud.wechat.wx.entity.message;

import lombok.Data;

@Data
public class VoiceMessage extends BaseMessage {
    // 媒体 ID
    private String MediaId;
    // 语音格式
    private String Format;
}
