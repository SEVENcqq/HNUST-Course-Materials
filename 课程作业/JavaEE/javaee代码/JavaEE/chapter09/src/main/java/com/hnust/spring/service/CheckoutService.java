package com.hnust.spring.service;

/**
 * ClassName: CheckoutService
 * Package: com.hnust.spring.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/2 - 14:24
 */
public interface CheckoutService {

    // 结账
    void checkout(Integer userId, Integer[] bookIds);
}
