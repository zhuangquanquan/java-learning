package cn.com.rivercloud.wechat.manage.controller;

import cn.com.rivercloud.wechat.common.lang.Result;
import cn.com.rivercloud.wechat.manage.common.dto.CustomerLineDto;
import cn.com.rivercloud.wechat.manage.common.validate.ValidateReg;
import cn.com.rivercloud.wechat.manage.service.impl.ManageTrialServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 试用订单
 */
@Slf4j
//@RequiresRoles("manage")
@RestController
@RequestMapping("/manage/trial")
public class ManageTrialController {

    @Autowired
    ManageTrialServiceImpl manageTrialService;

    @PostMapping(value = "/save")
    public Result save(@Validated @RequestBody CustomerLineDto customerLineDto, HttpServletRequest request, HttpServletResponse response) {
        boolean isValidOk = ValidateReg.isLineIP(customerLineDto.getLineIp());
        if (!isValidOk) {
            return Result.fail("专线IP格式错误");
        }
        if (!Strings.isEmpty(customerLineDto.getLineDomain()) && !ValidateReg.isLineDomain(customerLineDto.getLineDomain())) {
            return Result.fail("专线下网站格式错误");
        }
        return manageTrialService.save(customerLineDto);
    }


}
