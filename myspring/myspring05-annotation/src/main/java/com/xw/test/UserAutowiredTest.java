package com.xw.test;

import com.xw.pojo.Dog;
import com.xw.pojo.UserAutowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserAutowiredTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-autowired.xml");
        UserAutowired user = (UserAutowired) context.getBean("user");
        Dog dog = user.getDog();
        dog.say();
    }
}
