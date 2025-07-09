package com.hnust.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ClassName: User
 * Package: com.hnust.entity
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 21:15
 */

@Component("user")
@Scope("singleton")
public class User {
    @Value("7")
    private int id;
    @Value("cqq")
    private String name;
    @Value("7777777")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id="+id+",name="+name+",password="+password;
    }
}
