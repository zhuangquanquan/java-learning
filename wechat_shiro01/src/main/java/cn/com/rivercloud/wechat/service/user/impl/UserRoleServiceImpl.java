package cn.com.rivercloud.wechat.service.user.impl;

import cn.com.rivercloud.wechat.entity.user.UserRole;
import cn.com.rivercloud.wechat.mapper.user.UserRoleMapper;
import cn.com.rivercloud.wechat.service.user.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
