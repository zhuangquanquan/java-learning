package cn.com.rivercloud.wechat.service.impl;

import cn.com.rivercloud.wechat.mapper.UserMapper;
import cn.com.rivercloud.wechat.pojo.User;
import cn.com.rivercloud.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User validateAccount(String username, String password) {
        return userMapper.validateAccount(username, password);
    }
}
