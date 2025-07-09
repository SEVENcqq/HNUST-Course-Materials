package com.hnust.pojo;

import java.util.HashMap;

/**
 * ClassName: Order
 * Package: com.hnust.pojo
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/27 - 22:25
 */
public class Order {
    private String orderId;
    private String orderName;
    private HashMap<String, Product> productInfo;

    public HashMap<String, Product> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(HashMap<String, Product> productInfo) {
        this.productInfo = productInfo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
