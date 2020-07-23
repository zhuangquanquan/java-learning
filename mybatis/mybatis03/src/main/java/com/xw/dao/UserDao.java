package com.xw.dao;


import com.xw.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    public List<User> getUserList();

    public User getById(long id);

    public List<User> page(Map<String, Integer> map);

    public List<User> getUserByRowBounds();

}
