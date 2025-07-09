package com.hnust.spring.dao.impl;

import com.hnust.spring.dao.UserDao;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserDaoImpl
 * Package: com.hnust.spring.dao.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 14:34
 */
@Service
public class UserDaoImpl implements UserDao {
    public void save(){
        System.out.println("用户保存成功！");
    }
}
