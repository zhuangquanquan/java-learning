package com.xw.pojo;

public class Alias {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void say() {
        System.out.println("alias: " + name );
    }
}
