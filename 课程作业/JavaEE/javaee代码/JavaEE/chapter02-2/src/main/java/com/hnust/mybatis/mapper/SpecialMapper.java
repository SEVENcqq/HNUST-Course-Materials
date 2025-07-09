package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: SpecialMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 13:14
 */
public interface SpecialMapper {

    //模糊查询
    List<User> getUserByLike(@Param("like") String like);

    //批量删除
    void deleteMoreUser(@Param("ids") String ids);

    //动态设置表名
    List<User> getUserList(@Param("tableName") String tablename);
    //获取自增的主键
    void insertUser(User user);
}
