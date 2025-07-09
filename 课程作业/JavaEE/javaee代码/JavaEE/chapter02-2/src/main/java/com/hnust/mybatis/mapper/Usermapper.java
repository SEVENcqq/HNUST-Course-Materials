package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * ClassName: Usermapper
 * Package: com.hnust.mybatis.mapper.Usermapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 10:36
 */
public interface Usermapper {

    //根据用户名查询用户信息
    User getUserByUsername(String username);
    //验证登录
    User checkLogin(String username , String password);
    //自定义传参
    User checkLoginByMap(Map<String , Object> map);
    //添加用户信息
    void insertUser(User user);
    //利用注释来自定义参数，无需利用map手动添加信息
    User checkLoginByParam(@Param("username") String username , @Param("password") String password);


}
