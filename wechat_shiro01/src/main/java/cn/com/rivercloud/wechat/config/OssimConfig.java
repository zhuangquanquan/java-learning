package cn.com.rivercloud.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ossim")
public class OssimConfig {

    private String protocol;
    private String ip;
    private int port;
}
