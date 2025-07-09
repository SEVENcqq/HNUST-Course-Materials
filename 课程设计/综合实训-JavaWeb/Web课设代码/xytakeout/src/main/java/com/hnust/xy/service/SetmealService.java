package com.hnust.xy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.xy.dto.SetmealDto;
import com.hnust.xy.entity.Setmeal;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.hnust.xy.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 19:40
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联关系
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 批量操作套餐状态
     * @param status
     * @param ids
     */
    public void updateStatus(int status, List<Long> ids);

    /**
     * 根据id查询菜品信息及口味
     * @param id
     * @return
     */
    public SetmealDto getByIdWithDish(Long id);

    /**
     * 修改套餐信息和对应的分类信息
     * @param setmealDto
     * @return
     */
    public void updateWithDish(SetmealDto setmealDto);
}
