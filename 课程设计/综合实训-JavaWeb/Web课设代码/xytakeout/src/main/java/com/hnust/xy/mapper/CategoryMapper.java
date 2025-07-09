package com.hnust.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.xy.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: CategoryMapper
 * Package: com.hnust.xy.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 18:08
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
