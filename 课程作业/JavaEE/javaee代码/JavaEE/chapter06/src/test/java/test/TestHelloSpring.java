package test;

import com.itheima.HelloSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestHelloSpring
 * Package: test
 * Description:测试HelloSpring
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 18:49
 */
public class TestHelloSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloSpring helloSpring = (HelloSpring)applicationContext.getBean("helloSpring");
        helloSpring.show();
    }
}
