package com.xw.impl;

import com.xw.UserService;

public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("添加一個用戶");
    }

    public void delete() {
        System.out.println("刪除一個用戶");
    }

    public void update() {
        System.out.println("更新一個用戶");
    }

    public void query() {
        System.out.println("查詢一個用戶");
    }
}
