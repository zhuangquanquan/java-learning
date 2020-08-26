package cn.com.rivercloud.wechat.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 试用订单
 */
@Slf4j
@RequiresRoles("manage")
@RestController
@RequestMapping("/manage")
public class ManageTrialController {




}
