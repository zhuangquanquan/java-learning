package cn.com.rivercloud.wechat.wx.entity;

import lombok.Data;

@Data
public class Template {
    //模板ID
    private String template_id;
    //模板标题
    private String title;
    //模板所属行业的一级行业
    private String primary_industry;
    //模板所属行业的二级行业
    private String deputy_industry;
    //模板内容
    private String content;
    //模板示例
    private String example;
}
