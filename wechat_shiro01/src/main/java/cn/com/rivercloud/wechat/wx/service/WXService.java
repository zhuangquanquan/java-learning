package cn.com.rivercloud.wechat.wx.service;

import cn.com.rivercloud.wechat.common.exception.NoneUrlException;
import cn.com.rivercloud.wechat.config.WeChatConfig;
import cn.com.rivercloud.wechat.wx.common.UrlEnum;
import cn.com.rivercloud.wechat.wx.entity.AssessToken;
import cn.com.rivercloud.wechat.wx.entity.TemplateList;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        String str = "{\n" +
                "           \"touser\":\"oxhxl5m9oMuaWH0Cd53hYqJO-VCY\",\n" +
                "           \"template_id\":\"gPS2OaJCtsyYxX5VY88gRTiplQP7d0NFzjtYSeuDrEo\",\n" +
                "           \"url\":\"http://weixin.qq.com/download\",  \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜你购买成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"39.8元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"2014年9月22日\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"欢迎再次购买！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(str, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        System.out.println(resStr);
        return resStr;
    }




}
