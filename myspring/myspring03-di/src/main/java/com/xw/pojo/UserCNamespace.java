package com.xw.pojo;

public class UserCNamespace {

    private String name;
    private int age;

    public UserCNamespace(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void say() {
        System.out.println("C命名空间,name:" + name + ",age:" + age);
    }
}
