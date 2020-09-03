package cn.com.rivercloud.wechat.manage.service.impl;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.manage.common.dto.CustomerLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ManageTrialServiceImpl {

    @Autowired
    RestTemplate client;

    public Result save(CustomerLineDto customerLineDto) {
        log.info(customerLineDto.toString());
        return Result.succ("");
    }
}
