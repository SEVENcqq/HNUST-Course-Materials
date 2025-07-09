package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.User;

import java.util.List;

/**
 * ClassName: UserMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/13 - 19:41
 */
public interface UserMapper {
    //添加用户信息
    int insertUser();
    //修改用户信息
    void updateUser();
    //删除用户信息
    void deleteUser();
    //查询用户信息
    User getUserById();
    //查询所有用户信息
    List<User> getAllUser();
}
