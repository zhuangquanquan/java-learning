package cn.com.rivercloud.wechat.user.service.impl;

import cn.com.rivercloud.wechat.user.entity.Permission;
import cn.com.rivercloud.wechat.mapper.user.PermissionMapper;
import cn.com.rivercloud.wechat.user.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
