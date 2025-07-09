package com.hnust.controller;

import com.hnust.pojo.Order;
import com.hnust.pojo.Product;
import com.hnust.pojo.User;
import com.hnust.pojo.User2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ClassName: OrderController
 * Package: com.hnust.controller
 * Description: chapter12.3.3
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 23:11
 */
@Controller
public class OrderController {

    // 属性为对象类型的数据绑定
    @RequestMapping("/findOrderWithUser")
    public String findOrderWithUser(User user){
        String username = user.getUsername();
        String orderId = user.getOrder().getOrderId();
        System.out.println("username = " + username + ", orderId = " + orderId);
        return "success";
    }

    // 属性为List类型的数据捆绑
    @RequestMapping("/showOrders")
    public String showOrders(User2 user2){
        List<Order> orders = user2.getOrders();
        List<String> addressList = user2.getAddress();
        System.out.println("订单：");
        for(int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            String address = addressList.get(i);
            System.out.println("订单Id：" + order.getOrderId());
            System.out.println("订单名称：" + order.getOrderName());
            System.out.println("订单配送地址为：" + address);
        }
        return "success";
    }

    // 属性为Map类型的数据绑定
    @RequestMapping("/orderInfo")
    public String orderInfo(Order order){
        HashMap<String, Product> productInfo = order.getProductInfo();
        Set<String> keys = productInfo.keySet();
        System.out.println("订单Id：" + order.getOrderId());
        System.out.println("订单商品信息：");
        for(String key: keys){
            Product product = productInfo.get(key);
            String proId = product.getProId();
            String proName = product.getProName();
            System.out.println(key + "类~" + "商品id=" + proId +", 商品名称=" + proName);
        }
        return "success";
    }
}
