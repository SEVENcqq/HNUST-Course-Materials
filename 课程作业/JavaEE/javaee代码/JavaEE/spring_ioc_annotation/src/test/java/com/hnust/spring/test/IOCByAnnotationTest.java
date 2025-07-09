package com.hnust.spring.test;

import com.hnust.spring.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: IOCByAnnotationTest
 * Package: com.hnust.spring.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 14:35
 */
public class IOCByAnnotationTest {

    /*
        四个注解：
        @Component：将类标识为普通组件
        @Controller：将类标识为控制层组件
        @Service：将类标识为业务层组件
        @Repository：将类标识为持久层组件
        执行的过程中没有特别的区别，目的是为了让程序员方便阅读，下面三个都是第一个的拓展
    */

    /*
        1.默认情况：类名首字母小写就是bean的id。例如：UserController类对应的bean的id就是userController。
        2.自定义bean的id：可通过标识组件的注解的value属性设置自定义的bean的id
    */

    /*
        @Autowired:实现自动装配功能的注解
        标注的位置：
        1.标识在成员变量上，此时不需要设置成员变量的set方法
        2.标识在set方法上，有种setter方法注入的的感觉
        3.标识在构造方法上，有种构造方法注入的感觉
        原理：
        1.默认通过byType的方式，在ioc容器中通过类型匹配某个bean为属性赋值
        2.若有多个类型匹配的bean，此时会自动转换为byName的方式实现自动装配的效果
          即将要赋值的属性的属性名作为bean的id匹配某个bean为属性赋值
        3.若byType和byName的方式都无法实现自动装配，即ioc容器中有多个类型匹配的bean，
          且这些bean的id和要赋值的属性的属性名都不一致，此时抛异常：NoUniqueBeanDefinitionException
          此时要在需要赋值的属性上，添加上一个注解@Qualifier,且该注解不能单独使用，要结合@Autowired搭配使用
          通过该注解的value属性值来指定某个bean的id，将这个bean为属性赋值
    */


    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
        UserController userController = applicationContext.getBean("userController",UserController.class);
        // 这里前面的小驼峰就是id
       /* System.out.println(userController);
        UserService userService = applicationContext.getBean("userServiceImpl",UserService.class);
        System.out.println(userService);
        UserDao userDao = applicationContext.getBean("userDaoImpl",UserDao.class);
        System.out.println(userDao);*/
        userController.save();
    }
}
