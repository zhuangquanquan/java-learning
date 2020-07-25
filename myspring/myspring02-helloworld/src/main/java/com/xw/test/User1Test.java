package com.xw.test;

import com.xw.pojo.User1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User1Test {

    /**
     * 注入到属性
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-user1.xml");
        User1 user1 = (User1) context.getBean("user1");
        user1.say();
    }
}
