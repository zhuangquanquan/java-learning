package cn.com.rivercloud.wechat.wx.service;

import cn.com.rivercloud.wechat.common.exception.NoneUrlException;
import cn.com.rivercloud.wechat.config.WeChatConfig;
import cn.com.rivercloud.wechat.wx.common.UrlEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

@Slf4j
@Service
public class WeChatAuthService {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    RestTemplate client;

    private String url(UrlEnum urlEnum) {
        String urlPath = weChatConfig.getUrlMaps().get(urlEnum.getMethodName());
        if (Strings.isEmpty(urlPath)) {
            throw new NoneUrlException();
        }
        return urlPath;
    }

    /**
     * 参考：
     * https://www.cnblogs.com/sutao/p/8727019.html
     * https://www.cnblogs.com/lovebread/p/5513241.html
     */
    public String getCode(HttpServletResponse response) throws IOException {
        String url = String.format(url(UrlEnum.code), weChatConfig.getAppID(), weChatConfig.getRedirectUriCodeUrl());
        response.sendRedirect(url); //重定向到授权页面
        return url;
    }


    /**
     * 获取请求用户信息的access_token
     */
    public String codeExchangeAccessToken(String code) {
        try {
            String url = String.format(url(UrlEnum.codeExchangeAccessToken), weChatConfig.getAppID(), weChatConfig.getAppsecret(), code);
            String resStr = client.getForObject(url, String.class);
            System.out.println("getUserInfoAccessToken=>" + resStr);
            return resStr;
        } catch (Exception ex) {
            log.error("fail to request wechat access token. [error={}]", ex);
        }
        return "";
    }






}
