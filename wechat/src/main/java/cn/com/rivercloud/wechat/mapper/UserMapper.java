package cn.com.rivercloud.wechat.mapper;

import cn.com.rivercloud.wechat.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getUserList();

    User validateAccount(@Param("username") String username, @Param("password") String password);
}
