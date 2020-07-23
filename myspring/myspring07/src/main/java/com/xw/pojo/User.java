package com.xw.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class User {

    @Value("qingfeng")
    public String name="清風";

    @Value("qingsan")
    public void setName(String name) {
        this.name = name;
    }
}
