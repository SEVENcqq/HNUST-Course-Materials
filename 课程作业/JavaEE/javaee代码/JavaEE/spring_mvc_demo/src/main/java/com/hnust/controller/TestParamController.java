package com.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TestParamController {

    @RequestMapping("/param/servletAPI")
    public String testParam(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }
    @RequestMapping("/param")
    public String testParam(@RequestParam(value = "username",required = false,defaultValue = "hello")
                                        String userName, String password,
                            @RequestHeader("referer") String referer,
                            @CookieValue("JSESSIONID") String JSESSIONID){
        System.out.println("userName:"+userName+",password:"+password);
        System.out.println("referer:"+referer);
        System.out.println("JSESSIONID:"+JSESSIONID);
        return "success";
    }

}
