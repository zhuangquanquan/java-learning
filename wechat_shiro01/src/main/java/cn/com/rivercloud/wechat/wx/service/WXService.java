package cn.com.rivercloud.wechat.wx.service;

import cn.com.rivercloud.wechat.common.exception.NoneUrlException;
import cn.com.rivercloud.wechat.config.WeChatConfig;
import cn.com.rivercloud.wechat.wx.common.UrlEnum;
import cn.com.rivercloud.wechat.wx.common.dto.TemplateDataDto;
import cn.com.rivercloud.wechat.wx.common.dto.TemplateDto;
import cn.com.rivercloud.wechat.wx.entity.AssessToken;
import cn.com.rivercloud.wechat.wx.entity.TemplateList;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class WXService {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    RestTemplate client;

    AssessToken assessToken;

    private String url(UrlEnum urlEnum) {
        if (UrlEnum.accessToken != urlEnum) {
            if (Objects.isNull(assessToken) || assessToken.isExpired()) {
                assessToken = getToken();
            }
        }
        String urlPath = weChatConfig.getUrlMaps().get(urlEnum.getMethodName());
        if (Strings.isEmpty(urlPath)) {
            throw new NoneUrlException();
        }
        return urlPath;
    }

    public AssessToken getToken() {
        String url = String.format(url(UrlEnum.accessToken), weChatConfig.getAppID(), weChatConfig.getAppsecret());
        String resStr = client.getForObject(url, String.class);
        assessToken = JSONObject.parseObject(resStr, AssessToken.class);
        return assessToken;
    }

    public void getUserList() {
        String url = String.format(url(UrlEnum.userList), assessToken.getAccess_token(), "");
        String resStr = client.getForObject(url, String.class);
        System.out.println(resStr);
    }

    public TemplateList getTemplateList() {
        String url = String.format(url(UrlEnum.templateList), assessToken.getAccess_token());
        String resStr = client.getForObject(url, String.class);
        TemplateList templateList = JSONObject.parseObject(resStr, TemplateList.class);
        return templateList;
    }

    public String sendTemplate() {
        String url = String.format(url(UrlEnum.sendTemplateMessage), assessToken.getAccess_token());
        TemplateDto templateDto = new TemplateDto();
        Map<String,TemplateDataDto> data = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append("正式单-开通提醒\n");
        sb.append("6月20号\n");
        sb.append("客户公司名称:北京xx科技有限公司\n");
        sb.append("服务套餐:安全检测服务\n");
        sb.append("开通日期:2020.10.10\n");
        sb.append("到期日期:2021.01.09\n");
        data.put("message", new TemplateDataDto().setValue(sb.toString()));
        templateDto.setTouser("oxhxl5m9oMuaWH0Cd53hYqJO-VCY")
                .setTemplate_id(weChatConfig.getManageTemplateId())
                //.setUrl("http://www.baidu.com")
                .setAppid(weChatConfig.getAppID())
                .setData(data);
        String jsonStr = JSONUtil.toJsonStr(templateDto);
        System.out.println("jsonStr=>" + jsonStr);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        System.out.println(resStr);
        return resStr;
    }

    public void getTemplateId() {
        String url = String.format(url(UrlEnum.templateId), assessToken.getAccess_token());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String str = "{\"template_id_short\": \"f8YgMX3-eNKknbLBBENs80lyG2xMXJWlFpED32PkWjo\"}";
        HttpEntity<String> formEntity = new HttpEntity<String>(str, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        System.out.println(resStr);
    }

    public void getUser() {
        String url = String.format(url(UrlEnum.user), assessToken.getAccess_token(), "oxhxl5m9oMuaWH0Cd53hYqJO-VCY");
        String resStr = client.getForObject(url, String.class);
        System.out.println(resStr);
    }



}
