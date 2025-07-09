package com.hnust.mapper;

import com.hnust.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: UserMapper
 * Package: com.hnust.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/9 - 19:43
 */
public interface UserMapper {
    // 登录界面验证环节
    User queryUserByEmailAndPassword(User user);

    // 保存用户注册信息
    void saveUser(User user);

    // 根据邮箱获得用户信息
    User getUserByEmail(@Param("email") String email);

}
