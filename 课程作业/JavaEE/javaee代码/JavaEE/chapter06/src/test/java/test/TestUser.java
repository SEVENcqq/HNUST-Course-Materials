package test;

import com.itheima.User1;
import com.itheima.User2;
import com.itheima.User3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestUser1
 * Package: test
 * Description:测试构造方法注入和setter方法注入
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 19:07
 */
public class TestUser {
    public static void main(String[] args) {
        ApplicationContext applicationContext1 =
                new ClassPathXmlApplicationContext("applicationContext-User1.xml");
        ApplicationContext applicationContext2 =
                new ClassPathXmlApplicationContext("applicationContext-User2.xml");
        ApplicationContext applicationContext3 =
                new ClassPathXmlApplicationContext("applicationContext-User3.xml");
        User1 user1 = applicationContext1.getBean("user1",User1.class);
        User2 user2 = applicationContext2.getBean("user2", User2.class);
        User3 user3 = applicationContext3.getBean("user3", User3.class);
        System.out.println("构造方法注入展示：" + user1);
        System.out.println("setter方法注入展示：" + user2);
        System.out.println("组合方法注入展示：" + user3);
        //setter和构造方法可以混合使用，多个构造方法同时存在也不影响创建bean，有种在原Java文件里面构造对象的感觉

    }
}
