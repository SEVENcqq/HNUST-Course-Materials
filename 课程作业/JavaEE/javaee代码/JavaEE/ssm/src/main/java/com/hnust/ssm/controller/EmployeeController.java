package com.hnust.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.hnust.ssm.pojo.Employee;
import com.hnust.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * ClassName: EmployeeController
 * Package: com.hnust.ssm.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 23:07
 */

/**
 * 1.查询所有的员工信息 --> /employee--get
 * 2.查询员工的分页信息 --> /employee/page/{页码}--get
 * 3.根据id查询员工信息 --> /employee/{id}--get
 * 4.跳转到添加页面 --> /to/add--get
 * 5.添加员工信息 --> /employee--post
 * 6.修改员工信息 --> /employee--put
 * 7.删除员工信息 --> /employee/{id}--delete
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployeePage/{pageNum}")
    public String getAllEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model){
        // 获取员工分页信息
        PageInfo<Employee> page = employeeService.getAllEmployeePage(pageNum);
        // 将分页数据共享到请求域中
        model.addAttribute("page", page);
        return "employee_list";
    }

    @GetMapping("/getAllEmployee")
    public String getAllEmployee(Model model){
        List<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees", employees);
        return "employee_list";
    }

    @GetMapping("/getEmployeeById/{id}")
    public String getEmployeeById(@PathVariable("id") Integer id){
        return "hh";
    }
}
