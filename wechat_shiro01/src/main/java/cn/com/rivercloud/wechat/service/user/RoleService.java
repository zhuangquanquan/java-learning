package cn.com.rivercloud.wechat.service.user;

import cn.com.rivercloud.wechat.entity.user.Role;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<Role> {

    String selectRoleByUserId(long userId);
}
