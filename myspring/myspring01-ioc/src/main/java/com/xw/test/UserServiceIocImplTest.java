package com.xw.test;

import com.xw.dao.UserDao;
import com.xw.dao.UserDaoImpl;
import com.xw.dao.UserDaoMysqlImpl;
import com.xw.service.UserService;
import com.xw.service.UserServiceIocImpl;

public class UserServiceIocImplTest {

    public static void main(String[] args) {
        UserServiceIocImpl service = new UserServiceIocImpl();
        service.setUserDao(new UserDaoMysqlImpl());
        service.getUser();

        service.setUserDao(new UserDaoImpl());
        service.getUser();
    }
}
