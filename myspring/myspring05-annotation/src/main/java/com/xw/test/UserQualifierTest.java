package com.xw.test;

import com.xw.pojo.Dog;
import com.xw.pojo.UserQualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserQualifierTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-qualifier.xml");
        UserQualifier user = (UserQualifier) context.getBean("user");
        Dog dog = user.getDog();
        dog.say();
    }
}
