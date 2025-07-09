package com.hnust.xy.dto;

import com.hnust.xy.entity.Setmeal;
import com.hnust.xy.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
