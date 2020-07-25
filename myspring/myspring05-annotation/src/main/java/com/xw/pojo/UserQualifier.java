package com.xw.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserQualifier {

    @Autowired
    @Qualifier("dog1")
    private Dog dog;

    public Dog getDog() {
        return dog;
    }
}
