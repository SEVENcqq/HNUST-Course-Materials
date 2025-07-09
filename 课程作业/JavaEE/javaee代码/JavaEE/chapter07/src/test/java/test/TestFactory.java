package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestFactory
 * Package: test
 * Description:测试静态工厂实例化和实例工厂实例化
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 20:33
 */
public class TestFactory {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("静态工厂实例化："+applicationContext.getBean("bean_static"));
        System.out.println("实例工厂实例化："+applicationContext.getBean("bean_live"));
    }
}
