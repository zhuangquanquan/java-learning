package com.xw;

import javax.annotation.Resource;

public class User {
    @Resource(name="cat1")
    private Cat cat;
    @Resource(name="dog1")
    private Dog dog;
    private String str;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
