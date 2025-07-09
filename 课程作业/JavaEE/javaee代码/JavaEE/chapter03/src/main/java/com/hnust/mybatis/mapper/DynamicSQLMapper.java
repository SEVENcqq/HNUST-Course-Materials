package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClassName: DynamicSQLMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 22:51
 */
public interface DynamicSQLMapper {
    //根据条件查询员工信息,if语句拼接
    List<Emp> getEmpByIf(Emp emp);
    //choose,when,otherwise元素
    List<Emp> getEmpByChoose(Emp emp);
    //where元素
    List<Emp> getEmpByWhere(Emp emp);
    //trim元素
    List<Emp> getEmpByTrim(Emp emp);
    //set元素
    void updateEmpBySet(Emp emp);
    //foreach元素迭代数组
    List<Emp> getEmpByArray(Integer[] ids);
    //foreach迭代List
    List<Emp> getEmpByList(List ids);
    //foreach迭代Map
    List<Emp> getEmpByMap(Map conditionMap);
    //sql元素
    List<Emp> getEmpBySql(@Param("like") String like);

}
