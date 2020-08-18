package cn.com.rivercloud.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix ="system")
public class SystemConfig {

    /**
     * 是否开启验证码
     */
    private boolean captchaEnable = false;

    /**
     * 登陆多少次失败后开启验证码，前提是验证码是开启状态
     */
    private int captchaLoginFailNumEnable;

    /**
     * 不需要认证的url
     */
    private String[] ignoredTokenPath;


}
