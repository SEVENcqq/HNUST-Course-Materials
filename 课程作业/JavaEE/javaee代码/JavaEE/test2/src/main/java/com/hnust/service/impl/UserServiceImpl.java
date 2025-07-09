package com.hnust.service.impl;

import com.hnust.mapper.UserMapper;
import com.hnust.pojo.User;
import com.hnust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.hnust.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/9 - 19:43
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean login(User user) {
        if(userMapper.queryUserByEmailAndPassword(user) != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean register(User user) {
        String email = user.getEmail();
        User u = userMapper.getUserByEmail(email);
        if(u == null){
            // 说明可以注册
            userMapper.saveUser(user);
            return true;
        } else {
            // 说明用户已存在
            return false;
        }
    }

    @Override
    public String getUsernameByEmail(String email) {
        User user = userMapper.getUserByEmail(email);
        return user.getUsername();
    }
}
