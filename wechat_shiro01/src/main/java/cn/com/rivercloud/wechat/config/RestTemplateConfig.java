package cn.com.rivercloud.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

   /* @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

   @Bean
   public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
       return new RestTemplate(factory);
   }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        HttpsSSL factory = new HttpsSSL();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }
}
