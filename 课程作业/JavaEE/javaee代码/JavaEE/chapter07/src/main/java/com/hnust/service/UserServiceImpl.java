package com.hnust.service;

import com.hnust.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName: UserServiceImpl
 * Package: com.hnust.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 21:24
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource(name="userDao")
    private UserDao userDao;
    public void save(){
        this.userDao.save();
        System.out.println("执行UserServiceImpl.save()");
    }
}
