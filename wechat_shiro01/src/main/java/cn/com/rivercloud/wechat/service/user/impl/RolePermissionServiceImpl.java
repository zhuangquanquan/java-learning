package cn.com.rivercloud.wechat.service.user.impl;

import cn.com.rivercloud.wechat.entity.user.RolePermission;
import cn.com.rivercloud.wechat.mapper.user.RolePermissionMapper;
import cn.com.rivercloud.wechat.service.user.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
