package com.hnust.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnust.ssm.mapper.EmployeeMapper;
import com.hnust.ssm.pojo.Employee;
import com.hnust.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.hnust.ssm.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 23:58
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    // 获取员工列表信息
    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    // 获取员工列表的分页信息

    @Override
    public PageInfo<Employee> getAllEmployeePage(Integer pageNum) {
        // 开启分页功能
        PageHelper.startPage(pageNum, 5);
        // 查询所有的员工信息
        List<Employee> employees = employeeMapper.getAllEmployee();
        // 获取分页相关数据
        PageInfo<Employee> page = new PageInfo<>(employees, 2);
        return page;
    }
}
