package com.hnust.spring.controller;

import com.hnust.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: UserController
 * Package: com.hnust.spring.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 14:31
 */
//@Controller("controller") // 这里是组件的自定义名，这边说明了什么id，那么ioc中获取的对应的bean也要是该名
@Controller
public class UserController {
    @Autowired
    //@Qualifier("Service")
    private UserService userService;

    /*@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    /*@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    public void save(){
        userService.save();
    }
}
