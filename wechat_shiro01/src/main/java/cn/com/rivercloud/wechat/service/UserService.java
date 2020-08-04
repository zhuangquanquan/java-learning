package cn.com.rivercloud.wechat.service;

import cn.com.rivercloud.wechat.pojo.User;

public interface UserService {

    User getById(long id);

    User getByUserName(String username);

    String getRole(String username);
}
