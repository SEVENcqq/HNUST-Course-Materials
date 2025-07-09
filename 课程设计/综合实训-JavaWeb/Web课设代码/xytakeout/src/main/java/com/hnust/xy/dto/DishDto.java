package com.hnust.xy.dto;

import com.hnust.xy.entity.Dish;
import com.hnust.xy.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    //菜品所对应的口味信息数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
