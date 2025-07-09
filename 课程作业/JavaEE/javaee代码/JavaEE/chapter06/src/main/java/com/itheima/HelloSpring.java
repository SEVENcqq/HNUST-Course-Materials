package com.itheima;

/**
 * ClassName: HelloSpring
 * Package: com.itheima
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 18:47
 */
public class HelloSpring {
    private String userName;
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void show(){
        System.out.println(userName+"大小姐驾到统统闪开！");
    }
}
