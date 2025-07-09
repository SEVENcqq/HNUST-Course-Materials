package com.hnust.xy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.xy.common.R;
import com.hnust.xy.entity.Orders;

/**
 * ClassName: OrderService
 * Package: com.hnust.xy.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 17:03
 */
public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     * @return
     */
    public void submit(Orders orders);
}
