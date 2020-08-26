package cn.com.rivercloud.wechat.wx.entity.message;

import lombok.Data;

@Data
public class VideoMessage extends BaseMessage {

    private String MediaId; // 视频消息媒体 id，可以调用多媒体文件下载接口拉取数据
    private String ThumbMediaId; // 视频消息缩略图的媒体 id，可以调用多媒体文件下载接口拉取数据
}
