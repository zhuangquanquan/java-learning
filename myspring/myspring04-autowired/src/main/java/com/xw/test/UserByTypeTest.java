package com.xw.test;

import com.xw.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserByTypeTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-byType.xml");
        User userXml = (User) context.getBean("user");
        userXml.say();
    }
}
