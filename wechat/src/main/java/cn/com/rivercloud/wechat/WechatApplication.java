package cn.com.rivercloud.wechat;

import cn.com.rivercloud.wechat.common.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@MapperScan("cn.com.rivercloud.wechat.mapper")
public class WechatApplication {

    private static Logger logger = LoggerFactory.getLogger(WechatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }

}
