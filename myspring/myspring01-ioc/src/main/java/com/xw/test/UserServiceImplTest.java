package com.xw.test;

import com.xw.service.UserService;
import com.xw.service.UserServiceImpl;

public class UserServiceImplTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.getUser();
    }
}
