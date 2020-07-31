package cn.com.rivercloud.wechat.service.impl;

import cn.com.rivercloud.wechat.mapper.UserMapper;
import cn.com.rivercloud.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String getRole(String username) {
        return userMapper.getRole(username);
    }

    @Override
    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    @Override
    public String getRolePermission(String username) {
        return userMapper.getRolePermission(username);
    }

    @Override
    public String getPermission(String username) {
        return userMapper.getPermission(username);
    }
}
