package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: DeptMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 15:50
 */
public interface DeptMapper {
    //通过分布查询，查询员工以及所对应的部门信息第二步
    Dept getEmpAndDeptByStepTwo(@Param("deptId") Integer deptId);
    //通过Collection处理一对多的映射关系
    Dept getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);
    //通过分布查询，查询部门以及所对应的员工信息第一步
    Dept getDeptAndEmpByStepOne(@Param("deptId") Integer deptId);
}
