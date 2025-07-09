package com.itheima;

/**
 * ClassName: Username
 * Package: com.itheima
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/6 - 16:15
 */
public class User3 {
    private int id;
    private String name;
    private String password;

    public User3(){}

    public User3(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User3(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id="+ id +",name="+ name +",password="+ password;
    }
}
