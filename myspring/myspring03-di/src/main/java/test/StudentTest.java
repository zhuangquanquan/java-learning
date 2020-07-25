package test;

import com.xw.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-student.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }
}
