package com.hnust.pojo;

/**
 * ClassName: User
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/9 - 19:34
 */
public class User {

    private String email;
    private String password;
    private String username;

    public User() {
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
