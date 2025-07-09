package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Package: com.itheima.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 20:13
 */
public class UserServiceImpl implements UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean login(String name, String password) {
        return userDao.login(name,password);
    }
}
