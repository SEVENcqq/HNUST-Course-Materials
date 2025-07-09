package com.hnust.controller;

import com.hnust.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: PageController
 * Package: com.hnust.controller
 * Description: chapter12.4
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 23:32
 */
@Controller
public class PageController {
    // 返回值为void类型的页面跳转
    @RequestMapping("/success")
    public void showPageByVoid(){
        System.out.println("showPageByVoid running!!!");
    }

    // 返回值为String类型的页面跳转(不携带数据)
    @RequestMapping("/showPageByString")
    public String showPageByString(){
        System.out.println("showPageByString running!!!");
        return "register2";
    }

    // 用以下返回类型视图解析器不生效
    // forword:需要转发的资源路径
    @RequestMapping("/showPageByForword")
    public String showPageByForward(){
        System.out.println("showPageByForword running!!!");
        return "forward:orders.jsp";
    }

    // redirect:需要重定向的资源路径
    @RequestMapping("/showPageByRedirect")
    public String showPageByRedirect(){
        System.out.println("showPageByRedirect running!!!");
        return "redirect:https://www.baidu.com";
    }

    // 返回值为String类型的页面跳转(携带数据)
    @RequestMapping("/showPageByRequest")
    public String showPageByRequest(HttpServletRequest request){
        System.out.println("showPageByRequest running!!!");
        request.setAttribute("username","cqq");
        return "register3";
    }

    @RequestMapping("/showPageByModel")
    public String showPageByModel(Model model){
        System.out.println("showPageByModel running!!!");
        model.addAttribute("username","zhangsan");
        return "register3";
    }

    // 返回值类型为ModelAndView类型的页面跳转
    @RequestMapping("/showModelAndView")
    public ModelAndView showModelAndView(){
        System.out.println("showModelAndView running!!!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username","lisi");
        modelAndView.setViewName("register3");
        User user = new User();
        user.setPassword("7777777");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
