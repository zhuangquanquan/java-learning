package com.xw;

import com.xw.pojo.User1;
import com.xw.pojo.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-user2.xml");
        User1 user = (User1) context.getBean("user");
        user.say();

        User2 user2 = (User2) context.getBean("user2");
        System.out.println(user2.toString());

        User2 userT = (User2) context.getBean("userT");
        System.out.println(userT.toString());

        System.out.println(user2 == userT);
    }
}
