package com.xw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DogTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Dog dog1 = (Dog) context.getBean("dog");
        Dog dog2 = (Dog) context.getBean("dog");
        System.out.println(dog1 == dog2);
        System.out.println(dog1.name);
        System.out.println(dog2.name);
    }
}
