package cn.com.rivercloud.wechat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    String getRole(@Param("username") String username);

    String getPassword(@Param("username") String username);

    String getRolePermission(@Param("username") String username);

    String getPermission(@Param("username") String username);
}
