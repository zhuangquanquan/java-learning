package com.xw.dao;


import com.xw.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from my_user")
    List<User> getUserList();

    @Select("select * from my_user where id=#{tid}")
    User getUserById(@Param("tid") long id);


    @Insert("insert into my_user(id, username, password) values (#{id}, #{username}, #{password})")
    int insert(User user);

    @Update("update my_user set username=#{username}, password=#{password} where id=#{id}")
    int update(User user);


    @Delete("delete from my_user where id=#{tid}")
    int delete(@Param("tid") long tid);


}
