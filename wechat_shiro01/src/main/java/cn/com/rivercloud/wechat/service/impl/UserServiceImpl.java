package cn.com.rivercloud.wechat.service.impl;

import cn.com.rivercloud.wechat.entity.User;
import cn.com.rivercloud.wechat.mapper.UserMapper;
import cn.com.rivercloud.wechat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
