package com.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: InitController
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 22:54
 */
@Controller
public class InitController {
    @RequestMapping("/")
    public String protal(){
        return "index";
    }
}
