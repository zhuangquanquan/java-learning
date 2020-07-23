package com.xw;

import com.xw.impl.UserServiceImpl;

public class UserServiceTest {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.add();
    }
}
