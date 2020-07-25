package com.xw.pojo;

public class Hello {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("hello:" + name);
    }
}
