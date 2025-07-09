package com.hnust.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnust.mybatis.mapper.DeptMapper;
import com.hnust.mybatis.mapper.EmpMapper;
import com.hnust.mybatis.pojo.Emp;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: PageTest
 * Package: com.hnust.mybatis.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/31 - 15:07
 */
public class PageTest {
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
    public void testPage(){
        //PageHelper.startPage(1,4);
        Page<Object> page = PageHelper.startPage(1,4);
        // 查询功能开启之前开启分页功能
        List<Emp> list = empMapper.selectByExample(null);
        //list.forEach(System.out::println);
        // 查询功能之后可以获取的分页相关的所有数据
        PageInfo<Emp> pageInfo = new PageInfo<>(list,3);// navigatePages 表示导航分页的页码数
        System.out.println(pageInfo);
    }
}
