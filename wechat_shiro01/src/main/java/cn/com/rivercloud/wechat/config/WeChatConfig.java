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

    /**
     * 客户经理订单模板Id
     */
    private String manageTemplateId;

    /**
     * 用户告警模板Id
     */
    private String customerTemplateId;

    /**
     {{workListType,DATA}}
     {{date,DATA}}
     客户公司名称：{{realName,DATA}}
     服务套餐：{{serviceType,DATA}}
     开通日期：{{startDate,DATA}}
     到期日期：{{endDate,DATA}}
     */

}








