package com.hnust.xy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnust.xy.entity.Category;

/**
 * ClassName: CategoryService
 * Package: com.hnust.xy.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 18:50
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
