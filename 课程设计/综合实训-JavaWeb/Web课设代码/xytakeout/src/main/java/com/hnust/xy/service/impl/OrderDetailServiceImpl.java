package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.entity.OrderDetail;
import com.hnust.xy.mapper.OrderDetailMapper;
import com.hnust.xy.service.OrderDetailService;
import com.hnust.xy.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * ClassName: OrderDetailServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 17:06
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
