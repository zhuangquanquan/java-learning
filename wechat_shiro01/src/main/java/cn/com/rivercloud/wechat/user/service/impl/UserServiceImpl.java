package cn.com.rivercloud.wechat.user.service.impl;

import cn.com.rivercloud.wechat.user.common.constant.TableFieldConstant;
import cn.com.rivercloud.wechat.user.entity.User;
import cn.com.rivercloud.wechat.mapper.user.UserMapper;
import cn.com.rivercloud.wechat.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public User getOne(String username){
        User user = super.getOne(new QueryWrapper<User>().eq(TableFieldConstant.user_table_filed_userName, username));
        return user;
    }

    public User getUserById(long id) {
       return super.getById(id);
    }


}
