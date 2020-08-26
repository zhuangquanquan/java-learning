package cn.com.rivercloud.wechat.wx.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class WeChatAuthDto implements Serializable {

    @NotBlank(message = "signature不能为空")
    private String signature;

    @NotBlank(message = "timestamp不能为空")
    private String timestamp;

    @NotBlank(message = "nonce不能为空")
    private String nonce;

    @NotBlank(message = "echostr不能为空")
    private String echostr;
}
