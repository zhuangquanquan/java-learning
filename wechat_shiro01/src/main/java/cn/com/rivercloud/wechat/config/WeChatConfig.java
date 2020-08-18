package cn.com.rivercloud.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {

    private String token;

    private String appID;

    private String appsecret;

    private Map<String, String> urlMaps = new HashMap<>();

}








