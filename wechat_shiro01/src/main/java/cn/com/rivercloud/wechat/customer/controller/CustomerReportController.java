package cn.com.rivercloud.wechat.customer.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务报表
 */
@RequiresRoles("customer")
@RestController
@RequestMapping("/customer/report")
public class CustomerReportController {

    
}
