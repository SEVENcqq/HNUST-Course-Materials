package com.hnust.mybatis.test;

import com.hnust.mybatis.mapper.DeptMapper;
import com.hnust.mybatis.mapper.EmpMapper;
import com.hnust.mybatis.pojo.Emp;
import com.hnust.mybatis.pojo.EmpExample;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: MBGTest
 * Package: com.hnust.mybatis.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/31 - 14:07
 */
public class MBGTest {
    private DeptMapper deptMapper;
    private EmpMapper empMapper;
    private SqlSession sqlSession;
    @Before
    public void setUp(){
        sqlSession = SqlSessionUtil.getSqlSession();
        deptMapper = sqlSession.getMapper(DeptMapper.class);
        empMapper = sqlSession.getMapper(EmpMapper.class);
    }

    @After
    public void release(){
        sqlSession.close();
    }
    @Test
    public void testSelectByPrimaryKey(){
        System.out.println(empMapper.selectByPrimaryKey(1));
    }
    @Test
    public void testSelectAll(){
        // 无条件查询
        List<Emp> list = empMapper.selectByExample(null);
        list.forEach(System.out::println);
    }
    @Test
    public void testSelectByExample(){
        // 条件查询
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameEqualTo("zhangsan");
        empExample.or().andGenderEqualTo("女");
        List<Emp> list = empMapper.selectByExample(empExample);
        list.forEach(System.out::println);
    }
    @Test
    public void testUpdate(){
        Emp emp = new Emp(5,"zhaoliu",22,"女");
        //empMapper.updateByPrimaryKey(emp);
        empMapper.updateByPrimaryKeySelective(emp);
    }

}
