package com.hnust.xy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.xy.dto.DishDto;
import com.hnust.xy.entity.Dish;

import java.util.List;

/**
 * ClassName: DishService
 * Package: com.hnust.xy.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 19:39
 */
public interface DishService extends IService<Dish> {

    //新增菜品，同时插入对应的口味数据，需要两张表：dish，dish_flavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新相应的口味信息
    public void updateWithFlavor(DishDto dishDto);

    //删除菜品信息，包括批量删除
    public void removeWithFlavor(List<Long> ids);

    //批量修改菜品状态，包括起售停售
    public void updateStatus(int status, List<Long> ids);
}
