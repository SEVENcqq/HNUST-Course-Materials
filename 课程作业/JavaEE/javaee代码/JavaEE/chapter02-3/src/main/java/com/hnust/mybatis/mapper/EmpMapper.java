package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: EmpMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 15:49
 */
public interface EmpMapper {
    //根据id查询员工信息
    Emp getEmpByEmpId(@Param("empId") Integer empId);

    //获取员工以及所对应的部门信息，级联以及association
    Emp getEmpAndDeptByEmpId(@Param("empId") Integer empId);
    //通过分布查询，查询员工以及所对应的部门信息第一步
    Emp getEmpAndDeptByStepOne(@Param("empId") Integer empId);
    //通过分布查询，查询部门以及所对应的员工信息第二步
    Emp getDeptAndEmpByStepTwo(@Param("empId") Integer empId);
}
