package com.xw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");
        user.say();

        UserT user2 = (UserT) context.getBean("user2");
        System.out.println(user2.toString());

        UserT userT = (UserT) context.getBean("userT");
        System.out.println(userT.toString());

        System.out.println(user2 == userT);
    }
}
