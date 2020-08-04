package cn.com.rivercloud.wechat.service.impl;

import cn.com.rivercloud.wechat.mapper.UserMapper;
import cn.com.rivercloud.wechat.pojo.User;
import cn.com.rivercloud.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public String getRole(String username) {
        return userMapper.getRole(username);
    }

}
