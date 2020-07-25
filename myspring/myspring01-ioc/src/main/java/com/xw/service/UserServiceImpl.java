package com.xw.service;

import com.xw.dao.UserDao;
import com.xw.dao.UserDaoImpl;
import com.xw.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService {

    /**
     * 换数据源需要修改代码
     */
    //private UserDao userDao = new UserDaoImpl();
    private UserDao userDao = new UserDaoMysqlImpl();

    public void getUser() {
        userDao.getUser();
    }
}
