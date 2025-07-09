package com.hnust.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hnust.xy.entity.Employee;
import com.hnust.xy.mapper.EmployeeMapper;
import com.hnust.xy.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.hnust.xy.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/21 - 22:31
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
