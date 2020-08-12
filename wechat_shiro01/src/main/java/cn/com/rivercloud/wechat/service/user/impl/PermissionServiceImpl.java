package cn.com.rivercloud.wechat.service.user.impl;

import cn.com.rivercloud.wechat.entity.user.Permission;
import cn.com.rivercloud.wechat.mapper.user.PermissionMapper;
import cn.com.rivercloud.wechat.service.user.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
