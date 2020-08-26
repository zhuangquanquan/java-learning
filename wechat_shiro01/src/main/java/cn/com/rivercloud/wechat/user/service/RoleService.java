package cn.com.rivercloud.wechat.user.service;

import cn.com.rivercloud.wechat.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<Role> {

    String selectRoleByUserId(long userId);
}
