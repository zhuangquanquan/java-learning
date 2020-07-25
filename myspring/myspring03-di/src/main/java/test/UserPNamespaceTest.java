package test;

import com.xw.pojo.UserPNamespace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserPNamespaceTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-p.xml");
        UserPNamespace userP = (UserPNamespace) context.getBean("userP");
        userP.say();
    }
}
