package com.hnust.Controller;

import com.hnust.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * ClassName: UserController
 * Package: com.hnust.Controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/3 - 21:28
 */
@Controller
public class UserController {
    @Resource(name="userService")
    private UserService userService;
    public void save(){
        this.userService.save();
        System.out.println("执行UserController.save()");
    }
}
