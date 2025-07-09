package com.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maomao
 * @create 2022-07-26 10:16
 */
@Controller
public class TestViewController {

    @RequestMapping("test/view/thymeleaf") // thymeleaf视图
    public String testThymeleafView(){
        return "success";
    }
    @RequestMapping("test/view/forward") // internalResource视图
    public String testInternalResourceView(){
        return "forward:/test/model";
    }
    @RequestMapping("test/view/redirect") // Redirect视图
    public String testRedirectView(){
        return "redirect:/test/model";
    }
}
