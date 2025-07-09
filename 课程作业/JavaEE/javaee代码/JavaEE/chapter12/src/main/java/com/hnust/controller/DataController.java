package com.hnust.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnust.pojo.Product;
import com.hnust.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DataController
 * Package: com.hnust.controller
 * Description: chapter12.5
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/29 - 22:15
 */
@Controller
public class DataController {
    // 12.5.1普通字符串的回写
    @RequestMapping("/showDataByResponse")
    public void showDataByResponse(HttpServletResponse response){
        try {
            response.getWriter().write("response");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 12.5.2 JSON数据的回写
    // 对象数据转换成JSON数据后的回写
    @RequestMapping("/showDataByJSON")
    public void showDataByJSON(HttpServletResponse response){
        try {
            ObjectMapper om = new ObjectMapper();
            User user = new User();
            user.setUsername("cqq");
            user.setPassword("1234567");
            String json = om.writeValueAsString(user);
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 集合数据转换成JSON数据后的回写
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser() {
        User user = new User();
        user.setUsername("cqq");
        return user;
    }

    @RequestMapping("/addProducts")
    @ResponseBody
    public List<Product> addProducts(){
        Product p1 = new Product();
        p1.setProId("001");
        p1.setProName("斯蒂卡");
        Product p2 = new Product();
        p2.setProId("002");
        p2.setProName("蝴蝶");
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        return products;
    }
}
