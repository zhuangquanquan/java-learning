package com.xw.dao;


import com.xw.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> getUserList(Map<String, String> map);

}
