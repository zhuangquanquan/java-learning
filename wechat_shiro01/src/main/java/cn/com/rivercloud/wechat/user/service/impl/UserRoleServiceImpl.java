package cn.com.rivercloud.wechat.user.service.impl;

import cn.com.rivercloud.wechat.user.entity.UserRole;
import cn.com.rivercloud.wechat.mapper.user.UserRoleMapper;
import cn.com.rivercloud.wechat.user.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
