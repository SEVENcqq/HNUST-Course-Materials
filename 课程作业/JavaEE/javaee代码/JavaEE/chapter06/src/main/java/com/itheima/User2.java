package com.itheima;

/**
 * ClassName: User2
 * Package: com.itheima
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 19:30
 */
public class User2 {
    private int id;
    private String name;
    private String password;

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

