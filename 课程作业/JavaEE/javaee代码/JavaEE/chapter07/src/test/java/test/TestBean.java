package test;

import com.hnust.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestBean
 * Package: test
 * Description:测试Bean的创建方式
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 20:22
 */
public class TestBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean bean = applicationContext.getBean("bean",Bean.class);
        System.out.println(bean);
    }
}
