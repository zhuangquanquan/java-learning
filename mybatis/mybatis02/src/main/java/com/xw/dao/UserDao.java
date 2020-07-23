package com.xw.dao;


import com.xw.pojo.User;

import java.util.List;

public interface UserDao {

    public List<User> getUserList();

    public User getById(long id);

}
