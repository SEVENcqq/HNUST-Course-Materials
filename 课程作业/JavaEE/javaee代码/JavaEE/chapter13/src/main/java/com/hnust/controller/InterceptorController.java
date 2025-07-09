package com.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: InterceptorController
 * Package: com.hnust.controller
 * Description: chapter13.2.2~13.2.3
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 17:32
 */
@Controller
public class InterceptorController {
    @RequestMapping("/interceptorTest")
    public String interceptorTest(){
        System.out.println("interceptorTest Running!!!");
        return "success.jsp";
    }

    @RequestMapping("/interceptorTest2")
    public String interceptorTest2(){
        System.out.println("interceptorTest2 Running!!!");
        System.out.println(1/0);
        return "success.jsp";
    }
}
