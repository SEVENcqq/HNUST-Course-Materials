package com.hnust.controller;

import com.hnust.dao.EmployeeDao;
import com.hnust.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 思路：可以将操作界面的某个操作设置成超链接，然后点击超链接，进入相应的controller层，在该
 *      层进行dao层方法的调用，比如说增删改查，将获得的数据保存到域中，经过页面跳转，将数据传输到
 *      相应的界面中，进行视图的渲染。
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    // 展示所有员工信息
    @GetMapping("/employee")
    public String getAllEmployee(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "employee_list";
    }

    // 添加员工信息
    @PostMapping("/employee")
    public String addEmployee(Employee employee){
        // 保存员工信息
        employeeDao.save(employee);
        // 这里直接跳转到employee_list是没有数据的，所以要跳转到上面employee的控制层页面
        // 将数据再次执行一遍，从而获得添加数据后更新的页面
        // 采用重定向到列表功能：/employee
        return "redirect:/employee";
    }


    // 跳转到更新页面
    @GetMapping("/employee/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee",employee);
        return "employee_update";
    }

    // 更新员工信息
    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    // 删除员工信息
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employee";
    }

}
