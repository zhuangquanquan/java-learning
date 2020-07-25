package com.xw.test;

import com.xw.pojo.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User2Test {

    /**
     * 注入到构造函数
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-user2.xml");
        User2 user2 = (User2) context.getBean("user2");
        user2.say();
    }
}
