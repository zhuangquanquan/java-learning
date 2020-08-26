package cn.com.rivercloud.wechat.user.service.impl;

import cn.com.rivercloud.wechat.user.entity.RolePermission;
import cn.com.rivercloud.wechat.mapper.user.RolePermissionMapper;
import cn.com.rivercloud.wechat.user.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
