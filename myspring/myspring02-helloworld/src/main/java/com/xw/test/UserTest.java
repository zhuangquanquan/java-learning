package com.xw.test;

import com.xw.pojo.User;
import com.xw.pojo.User1;
import com.xw.pojo.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    /**
     * beans import
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-user.xml");
        User user = (User) context.getBean("user");
        user.say();

        User1 user1 = (User1) context.getBean("user1");
        user1.say();

        User2 user2 = (User2) context.getBean("user2");
        user2.say();
    }
}
