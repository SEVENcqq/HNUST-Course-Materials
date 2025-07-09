package com.hnust.controller;

import com.hnust.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * ClassName: UserController
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 17:44
 */
@Controller
public class UserController {
    // 跳转到首页
    @RequestMapping("/main")
    public String toMainPage(){
        return "main.jsp";
    }

    // 跳转到登录页面
    @RequestMapping("/toLogin")
    public String toLoginPage(){
        return "login.jsp";
    }

    // 跳转到订单信息页面
    @RequestMapping("/orderInfo")
    public String orderInfo(){
        return "orderInfo.jsp";
    }

    // 用户登录
    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();
        // 模拟从数据库中获取用户名和密码后进行判断
        if (username != null && "cqq".equals(username) && password != null && "123456".equals(password)){
            // 将用户对象添加到Session
            session.setAttribute("user",user);
            // 登录成功返回至首页
            return "main";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "login.jsp";
    }

    // 用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        // 清除Session
        session.invalidate();
        // 退出登录后重定向到登录界面
        return "redirect: /chapter13/toLogin";
    }
}
