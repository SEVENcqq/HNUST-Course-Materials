package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.common.CustomException;
import com.hnust.xy.dto.DishDto;
import com.hnust.xy.entity.Dish;
import com.hnust.xy.entity.DishFlavor;
import com.hnust.xy.entity.Setmeal;
import com.hnust.xy.entity.SetmealDish;
import com.hnust.xy.mapper.DishMapper;
import com.hnust.xy.service.DishFlavorService;
import com.hnust.xy.service.DishService;
import com.hnust.xy.service.SetmealDishService;
import com.hnust.xy.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DishServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 19:41
 */

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId();//菜品id

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息，从dish表查询
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    /**
     * 更新菜品信息，同时更新相应的口味信息
     * @param dishDto
     */
    @Override
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);

        //清理当前菜品相应口味数据---dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //添加当前菜品相应口味数据---dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 删除菜品信息，包括批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithFlavor(List<Long> ids) {
        //select count(*) from dish where id in (1,2,3) and status = 1
        //查询菜品状态，确定是否可以删除
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId,ids);
        queryWrapper.eq(Dish::getStatus,1);

        int count = this.count(queryWrapper);

        //如果不能删除，抛出一个业务异常
        if(count > 0){
            throw new CustomException("菜品正在售卖中，删除失败");
        }

        //如果可以删除，先删除菜品表中的数据
        this.removeByIds(ids);

        //删除关系表中的数据---dish_flavor
        //delete from dish_flavor where dish_id in (1,2)
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(lambdaQueryWrapper);
    }

    /**
     * 批量修改菜品状态，包括起售停售
     * @param status
     * @param ids
     */
    @Override
    @Transactional
    public void updateStatus(int status, List<Long> ids) {

        //遍历修改菜品状态
        for(Long id: ids){
            Dish dish = dishService.getById(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        }

        //由于菜品和套餐有关联关系的，所已停售的菜品所联系的套餐也需被修改，换句话来讲，就是菜品被修改成停售状态，那么所关联的套餐也许被修改成停售状态
        //但是，菜品被修改成起售状态，关联的套餐不一定被修改成起售状态，这里可能涉及到其它菜品还处于停售状态或者本套餐本就属于停售状态

        //这里其实可以在前台加一个确定是否要停售菜品
        if(status == 0){
            //先从setmeal_dish表中找到dish与setmeal关联的套餐id
            LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SetmealDish::getDishId,ids);

            //SQL:select * from setmeal_dish where dish_id in (1,2,3)
            List<SetmealDish> list = setmealDishService.list(queryWrapper);

            for(SetmealDish temp: list){
                //然后在setmeal表中修改其status值
                Setmeal setmeal = setmealService.getById(temp.getSetmealId());
                setmeal.setStatus(status);
                setmealService.updateById(setmeal);
            }

        }

    }
}
