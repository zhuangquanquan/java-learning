package cn.com.rivercloud.wechat.mapper.user;


import cn.com.rivercloud.wechat.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Role> {

    String selectRoleByUserId(@Param("userId") long userId);
}

