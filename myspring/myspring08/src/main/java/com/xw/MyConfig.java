package com.xw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class MyConfig {

    @Bean
    @Scope("prototype")
    public Dog dog() {
        return new Dog();
    }
}
