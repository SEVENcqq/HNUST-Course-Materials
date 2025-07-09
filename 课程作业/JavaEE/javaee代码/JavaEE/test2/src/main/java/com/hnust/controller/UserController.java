package com.hnust.controller;

import com.hnust.pojo.User;
import com.hnust.service.UserService;
import com.hnust.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassName: UserController
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/9 - 19:41
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        boolean flag = userService.login(user);
        if(flag == true){
            String username = userService.getUsernameByEmail(user.getEmail());
            session.setAttribute("user", user);
            return "redirect:/getAllSchool/1" + '/' +username;
        } else {
            model.addAttribute("msg", "邮箱或密码错误！");
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            return "login";
        }
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toLogin";
    }

    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model, HttpServletRequest request){
        if(!CodeUtil.checkVerifyCode(request)){
            model.addAttribute("msg", "验证码有误！");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("email", user.getEmail());
            return "register";
        } else {
            boolean flag = userService.register(user);
            if(flag == false){
                model.addAttribute("msg", "用户邮箱已存在！");
                model.addAttribute("username", user.getUsername());
                model.addAttribute("password", user.getPassword());
                model.addAttribute("email", user.getEmail());
                return "register";
            } else {
                return "redirect:/toLogin";
            }
        }
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }
}
