package com.xw.pojo;

import javax.annotation.Resource;

public class UserResource {

    @Resource(name="dog1")
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
