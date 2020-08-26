package cn.com.rivercloud.wechat.wx.entity.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 <xml>
 <ToUserName><![CDATA[gh_f8f2b2f70dd7]]></ToUserName>
 <FromUserName><![CDATA[oxhxl5m9oMuaWH0Cd53hYqJO-VCY]]></FromUserName>
 <CreateTime>1597649671</CreateTime>
 <MsgType><![CDATA[text]]></MsgType>
 <Content><![CDATA[11]]></Content>
 <MsgId>22872841973945452</MsgId>
 </xml>
 */
@Accessors(chain = true)
@Data
public class TextMessage extends BaseMessage {

    private String Content;

}
