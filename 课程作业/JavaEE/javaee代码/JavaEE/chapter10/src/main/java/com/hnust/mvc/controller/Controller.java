package com.hnust.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: Controller
 * Package: com.hnust.mvc.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/20 - 21:51
 */
@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/")
    public String protal(){
        return "test";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "test2";
    }
}
