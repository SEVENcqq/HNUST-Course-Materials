package com.hnust.dao;

import com.hnust.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserDaoImpl
 * Package: com.hnust.dao
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 21:22
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{
    @Override
    public void save() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        User user = applicationContext.getBean("user",User.class);
        System.out.println(user);
        System.out.println("执行UserDaoImpl.save()");
    }
}
