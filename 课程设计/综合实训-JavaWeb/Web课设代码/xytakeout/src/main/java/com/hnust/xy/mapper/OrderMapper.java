package com.hnust.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.xy.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.hnust.xy.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/26 - 17:02
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
