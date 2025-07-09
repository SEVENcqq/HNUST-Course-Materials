package com.hnust.service;

import com.hnust.pojo.User;

/**
 * ClassName: UserService
 * Package: com.hnust.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/9 - 19:42
 */
public interface UserService {
    boolean login(User user);

    boolean register(User user);

    String getUsernameByEmail(String email);
}
