package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SelectMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 12:30
 */
public interface SelectMapper {
    //根据id查询用户信息
    User getUserById(@Param("id") Integer id);
    //查询所有用户信息
    List<User> getAllUser();
    //查询数量
    Integer getCount();
    //查询结果未含具体实体类(如最高薪资)
    Map<String , Object> getUserByIdToMap(@Param("id") Integer id);
    //查询所有用户信息为map集合,方法一
    List<Map<String , Object>> getAllUserToMap();
    //查询所有用户信息为map集合,方法二
    @MapKey("id")
    Map<String , Object> getAllUserToMap2();
}
