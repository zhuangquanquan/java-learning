package com.xw.test;

import com.xw.pojo.Dog;
import com.xw.pojo.UserResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserResourceTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-resource.xml");
        UserResource user = (UserResource) context.getBean("user");
        Dog dog = user.getDog();
        dog.say();
    }
}
