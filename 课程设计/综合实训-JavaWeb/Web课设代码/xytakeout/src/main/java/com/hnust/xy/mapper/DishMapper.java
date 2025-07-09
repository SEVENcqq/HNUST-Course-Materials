package com.hnust.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.xy.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: DishMapper
 * Package: com.hnust.xy.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 19:37
 */

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
