package com.xw.pojo;

public class User {

    private String name;

    public User() {
        System.out.println("进入User的无参构造");
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("user ：" + name);
    }

}
