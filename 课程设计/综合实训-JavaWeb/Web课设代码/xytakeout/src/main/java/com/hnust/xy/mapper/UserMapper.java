package com.hnust.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnust.xy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: com.hnust.xy.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/25 - 15:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
