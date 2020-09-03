package cn.com.rivercloud.wechat.manage.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HttpService {

    @Autowired
    RestTemplate client;

    public String conn(String url, String username, String password) {
        String jsonStr = "{\"username\": \""+username+"\", \"password\": \""+password+"\"}";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> formEntity = new HttpEntity<>(jsonStr, headers);
        String resStr = client.postForObject(url, formEntity, String.class);
        JSONObject result = JSONObject.parseObject(resStr);
        log.info("获取token信息:{}", result);
        if (result.getBoolean("success")) {
            return result.getString("token");
        }
        return "";
    }
















}
