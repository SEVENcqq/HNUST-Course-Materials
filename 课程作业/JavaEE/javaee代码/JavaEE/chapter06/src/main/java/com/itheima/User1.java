package com.itheima;

/**
 * ClassName: User1
 * Package: com.itheima
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 18:54
 */
public class User1 {
    private int id;
    private String name;
    private String password;

    public User1(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
