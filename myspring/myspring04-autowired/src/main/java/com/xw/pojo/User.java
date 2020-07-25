package com.xw.pojo;

public class User {

    private String type;
    private Cat cat;

    public void setType(String type) {
        this.type = type;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void say() {
        cat.say(type);
    }
}
