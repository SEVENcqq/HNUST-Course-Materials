package com.hnust.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hnust.ssm.pojo.Employee;

import java.util.List;

/**
 * ClassName: EmployeeService
 * Package: com.hnust.ssm.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 23:58
 */
public interface EmployeeService {
    List<Employee> getAllEmployee();

    PageInfo<Employee> getAllEmployeePage(Integer pageNum);
}
