package cn.com.rivercloud.wechat.service;

public interface UserService {

    String getRole(String username);

    String getPassword(String username);
}
