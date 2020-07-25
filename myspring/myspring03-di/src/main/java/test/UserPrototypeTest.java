package test;

import com.xw.pojo.UserPNamespace;
import com.xw.pojo.UserPrototype;
import com.xw.pojo.UserSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserPrototypeTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-prototype.xml");
        UserPrototype userPrototype1 = (UserPrototype) context.getBean("userPrototype");
        UserPrototype userPrototype2 = (UserPrototype) context.getBean("userPrototype");
        System.out.println("prototype模式：" + (userPrototype1==userPrototype2));
    }
}
