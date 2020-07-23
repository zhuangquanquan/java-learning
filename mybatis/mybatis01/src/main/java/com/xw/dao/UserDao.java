package com.xw.dao;

import com.xw.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> getUserList();

    public User getById(long id);

    public int insert(User uer);

    public int update(User user);

    public int delete(long id);

    public List<User> getLikeUserList(String name);

    public List<User> getMapUserList(Map<String, String> map);

}
