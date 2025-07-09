package com.hnust.spring.service.impl;

import com.hnust.spring.dao.UserDao;
import com.hnust.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserServiceImpl
 * Package: com.hnust.spring.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 14:33
 */
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public void save(){
        userDao.save();
    }
}
