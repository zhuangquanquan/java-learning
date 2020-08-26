package cn.com.rivercloud.wechat.wx.entity.message;

import lombok.Data;

@Data
public class ImageMessage extends BaseMessage {

    // 圖片鏈接
    private String PicUrl;
}
