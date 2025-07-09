package test;

import com.hnust.Controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestXml
 * Package: test
 * Description:测试注解的装配
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 21:33
 */
public class TestXml {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContextXml.xml");
        UserController userController = applicationContext.getBean("userController",UserController.class);
        userController.save();
    }
}
