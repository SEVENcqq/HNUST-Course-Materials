package com.hnust.pojo;

import java.util.List;

/**
 * ClassName: User2
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 22:37
 */
public class User2 {
    private String username;
    private String password;
    private List<Order> orders;
    private List<String> address;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
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

}
