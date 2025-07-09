package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.entity.User;
import com.hnust.xy.mapper.UserMapper;
import com.hnust.xy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/25 - 15:09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
