package com.hnust.controller;

import com.hnust.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * ClassName: UserController
 * Package: com.hnust.controller
 * Description:  chapter12.2
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 19:28
 */
@Controller
public class UserController {
    //12.2.1 默认类型数据绑定
    @RequestMapping("/findUserById")
    public String findUserById(HttpServletRequest request){
        String userid = request.getParameter("userid");
        System.out.println("id = " + userid);
        return "success";
    }

    //12.2.2 简单数据类型绑定
    @RequestMapping("/getUserNameAndId")
    public String getUserNameAndId(String username,Integer id){
        System.out.println("username = " + username + ", id = " + id);
        return "success";
    }

    // @RequestParam注解
    @RequestMapping("/getUserName")
    public String getUserName(@RequestParam(value = "name", required = false, defaultValue = "cqq") String username){
        System.out.println("username = " + username);
        return "success";
    }

    // @PathVariable注解
    @RequestMapping("/getPathVariable/{name}")
    public String getPathVariable(@PathVariable(value = "name") String username){
        System.out.println("username = " + username);
        return "success";
    }

    // 12.2.3 POJO绑定
    @RequestMapping("/registerUser")
    public String registerUser(User user)  {
        // get请求中乱码解决方案,其实这里不需要转换编码也可以运行成功
        /*String username = null;
        try {
            username = new String(user.getUsername().getBytes("ISO8859-1"),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("username = " + username + ", password = " + password);
        return "success";
    }

    // 12.2.4 自定义类型转换器
    //方式一：
    /*@RequestMapping("/getBirthday")
    public String getBirthday(Date birthday){
        System.out.println("出生日期方式一：" + birthday);
        return "success";
    }*/
    //方式二：
    @RequestMapping("/getBirthday")
    public String getBirthday(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
        System.out.println("出生日期方式二：" + birthday);
        return "success";
    }
    //方式三：
    @RequestMapping("/getBirthdayByUser")
    public String getBirthdayByUser(User user){
        System.out.println("出生日期方式三：" + user.getBirthday());
        return "success";
    }

}
