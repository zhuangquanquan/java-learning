package cn.com.rivercloud.wechat.service;

import cn.com.rivercloud.wechat.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    User validateAccount(String username, String password);

    User getByUserName(String username);

    String getRole(String username);

    String getPassword(String username);
}
