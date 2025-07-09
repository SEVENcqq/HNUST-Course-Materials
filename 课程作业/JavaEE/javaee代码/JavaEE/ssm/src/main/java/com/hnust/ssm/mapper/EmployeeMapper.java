package com.hnust.ssm.mapper;

import com.hnust.ssm.pojo.Employee;

import java.util.List;

/**
 * ClassName: EmployeeMapper
 * Package: com.hnust.ssm.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/4 - 14:08
 */
public interface EmployeeMapper {
    List<Employee> getAllEmployee();
}
