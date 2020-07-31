package cn.com.rivercloud.wechat.service;

import org.apache.ibatis.annotations.Param;

public interface UserService {

    String getRole(String username);

    String getPassword(String username);

    String getRolePermission(@Param("username") String username);

    String getPermission(@Param("username") String username);
}
