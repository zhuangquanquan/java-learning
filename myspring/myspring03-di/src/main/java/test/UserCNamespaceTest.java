package test;

import com.xw.pojo.UserCNamespace;
import com.xw.pojo.UserPNamespace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserCNamespaceTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-c.xml");
        UserCNamespace userC = (UserCNamespace) context.getBean("userC");
        userC.say();
    }
}
