package com.hnust.controller;

import com.hnust.pojo.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName: ProductController
 * Package: com.hnust.controller
 * Description: chapter12.3.1~12.3.2 and 12.3.4
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 21:51
 */
@Controller
public class ProductController {

    // 数组绑定
    /*@RequestMapping("/getProducts")
    public String getProducts(String[] proIds){
        for(String proId: proIds){
            System.out.println("数组绑定获取了proId为" + proId + "的商品");
        }
        return "success";
    }*/

    // 集合绑定
    @RequestMapping("/getProducts")
    public String getProducts(@RequestParam("proIds") List<String> proIds){
        for(String proId: proIds){
            System.out.println("集合绑定获取了proId为" + proId + "的商品");
        }
        return "success";
    }

    // JSON数据绑定
    @RequestMapping("/getProduct")
    public void getProduct(@RequestBody Product product){
        String proId = product.getProId();
        String proName = product.getProName();
        System.out.println("proId: " + proId + ", proName: " + proName);
    }

    @RequestMapping("/getProductList")
    public void getProductList(@RequestBody List<Product> products){
        for(Product product: products){
            String proId = product.getProId();
            String proName = product.getProName();
            System.out.println("获取了 proId: " + proId + ", proName: " + proName);
        }
    }
}
