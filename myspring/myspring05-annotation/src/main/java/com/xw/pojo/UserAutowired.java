package com.xw.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserAutowired {

    @Autowired
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
