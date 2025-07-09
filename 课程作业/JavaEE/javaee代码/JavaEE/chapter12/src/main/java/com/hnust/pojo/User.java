package com.hnust.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * ClassName: User
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 20:46
 */
public class User {
    private String username;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
