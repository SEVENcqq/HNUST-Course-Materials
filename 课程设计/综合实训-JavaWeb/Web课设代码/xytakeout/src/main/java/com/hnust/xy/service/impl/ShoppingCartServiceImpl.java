package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.common.BaseContext;
import com.hnust.xy.entity.ShoppingCart;
import com.hnust.xy.mapper.ShoppingCartMapper;
import com.hnust.xy.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * ClassName: ShoppingCartServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 16:07
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
