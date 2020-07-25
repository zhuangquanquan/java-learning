package test;

import com.xw.pojo.UserPNamespace;
import com.xw.pojo.UserSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserSingletonTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-singleton.xml");
        UserSingleton userSingleton1 = (UserSingleton) context.getBean("userSingleton");
        UserSingleton userSingleton2 = (UserSingleton) context.getBean("userSingleton");
        System.out.println("singleton模式：" + (userSingleton1==userSingleton2));
    }
}
