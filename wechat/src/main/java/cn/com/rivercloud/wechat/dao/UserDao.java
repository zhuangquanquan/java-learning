package cn.com.rivercloud.wechat.dao;

import cn.com.rivercloud.wechat.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> getUserList();
}
