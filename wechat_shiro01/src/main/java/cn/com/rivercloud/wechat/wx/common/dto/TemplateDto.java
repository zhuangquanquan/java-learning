package cn.com.rivercloud.wechat.wx.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain=true)
public class TemplateDto {

    private String touser;
    private String template_id;
    private String url;
    private String appid;
    private Map<String,TemplateDataDto> data;
    private String color;
}

