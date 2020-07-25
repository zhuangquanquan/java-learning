package com.xw.service;

import com.xw.dao.UserDao;

public class UserServiceIocImpl implements UserService {

    private UserDao userDao;

    /**
     * 换数据源改成了在客户端上
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void getUser() {
        userDao.getUser();
    }
}
