package com.xw.pojo;

public class User1 {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("user1 :" + name);
    }
}
