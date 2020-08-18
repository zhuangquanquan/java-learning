package cn.com.rivercloud.wechat.wx.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class TemplateDataDto {
    private String value;
    private String color;
}
