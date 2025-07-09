package com.itheima.dao.impl;

import com.itheima.dao.UserDao;

/**
 * ClassName: UserDaoImpl
 * Package: com.itheima.dao.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 20:09
 */
public class UserDaoImpl implements UserDao {
    public boolean login(String name, String password) {
        if(name.equals("cqq")&&password.equals("7777777")) {
            return true;
        } else {
            return false;
        }
    }
}
