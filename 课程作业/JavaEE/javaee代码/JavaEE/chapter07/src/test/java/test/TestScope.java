package test;

import com.hnust.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestScope
 * Package: test
 * Description:测试Bean的作用域
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 20:46
 */
public class TestScope {
    public static void main(String[] args) {
        ApplicationContext applicationContextScope = new ClassPathXmlApplicationContext("applicationContextScope.xml");
        Bean bean_singleton1 = applicationContextScope.getBean("bean_singleton",Bean.class);
        Bean bean_singleton2 = applicationContextScope.getBean("bean_singleton",Bean.class);

        Bean bean_prototype1 = applicationContextScope.getBean("bean_prototype",Bean.class);
        Bean bean_prototype2 = applicationContextScope.getBean("bean_prototype",Bean.class);
        System.out.println("singleton作用域："+ (bean_singleton1==bean_singleton2));
        System.out.println("prototype作用域："+ (bean_prototype1==bean_prototype2));

    }
}
