package com.hnust.spring.test;

import com.hnust.spring.controller.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName: TxByAnnotationTest
 * Package: com.hnust.spring.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 13:01
 */

/**
 * 声明式事务的配置步骤：
 * 1.在spring的配置文件中配置事务管理器
 * 2.开启事务的注解驱动
 *   在需要被事务管理的方法上，添加@Transactional注解，该方法就会被事务管理
 * @Transactional标识的位置：
 * 1.标识在方法上，则只会影响该方法
 * 2.标识的类上，则会影响类中所有的方法
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx-annotation.xml")
public class TxByAnnotationTest {
    @Autowired
    private BookController bookController;
    @Test
    public void testBuyBook(){
        bookController.buyBook(1,2);
    }

    @Test
    public void testBuyBooks(){
        bookController.checkout(1,new Integer[]{1,2});
    }
}
