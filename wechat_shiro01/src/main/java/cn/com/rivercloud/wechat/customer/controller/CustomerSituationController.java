package cn.com.rivercloud.wechat.customer.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 安全态势
 */
@RequiresRoles("customer")
@RestController
@RequestMapping("/customer/situation")
public class CustomerSituationController {


}
