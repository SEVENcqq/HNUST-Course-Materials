package com.hnust.XML;

/**
 * ClassName: UserDaoImpl
 * Package: com.hnust.XML
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/4 - 13:42
 */
public class UserDaoImpl implements UserDao{
    public void insert(){
        System.out.println("添加用户信息");
    }
    public void delete(){
        System.out.println("删除用户信息");
    }
    public void update(){
        System.out.println("更新用户信息");
    }
    public void select(){
        System.out.println("查询用户信息");
    }
}
