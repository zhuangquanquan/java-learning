package com.xw.test;

import com.xw.pojo.Alias;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AliasTest {

    /**
     * bean 别名
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-alias.xml");
        Alias alias = (Alias) context.getBean("alias");
        alias.say();

        Alias alias2 = (Alias) context.getBean("alias2");
        alias2.say();

        Alias alias3 = (Alias) context.getBean("alias3");
        alias3.say();

        Alias alias4 = (Alias) context.getBean("alias4");
        alias4.say();

        Alias alias5 = (Alias) context.getBean("alias5");
        alias5.say();

        Alias alias6 = (Alias) context.getBean("alias6");
        alias6.say();
    }
}
