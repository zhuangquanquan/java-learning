package cn.com.rivercloud.wechat.mapper;

import cn.com.rivercloud.wechat.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User getById(long id);

    User getByUserName(@Param("username") String username);

    String getRole(@Param("username") String username);

}
