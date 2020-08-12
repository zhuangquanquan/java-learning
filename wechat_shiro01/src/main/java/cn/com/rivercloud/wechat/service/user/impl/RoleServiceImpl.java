package cn.com.rivercloud.wechat.service.user.impl;

import cn.com.rivercloud.wechat.entity.user.Role;
import cn.com.rivercloud.wechat.mapper.user.RoleMapper;
import cn.com.rivercloud.wechat.service.user.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public String selectRoleByUserId(long userId) {
        return baseMapper.selectRoleByUserId(userId);
    }
}
