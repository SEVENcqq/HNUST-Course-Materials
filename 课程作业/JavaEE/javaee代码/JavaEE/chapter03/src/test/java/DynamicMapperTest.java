import com.hnust.mybatis.mapper.DynamicSQLMapper;
import com.hnust.mybatis.pojo.Emp;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: DynamicMapperTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 23:04
 */
public class DynamicMapperTest {
    //if语句拼接
    @Test
    public void testGetEmpByIf(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(1,"cqq",20,"女");
        List<Emp> emps = dynamicSQLMapper.getEmpByIf(emp);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //choose,when,otherwise元素
    @Test
    public void testGetEmpByChoose(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(1,"a",20,"男");
        List<Emp> emps = dynamicSQLMapper.getEmpByChoose(emp);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //where元素
    @Test
    public void testGetEmpByWhere(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(2,"lisi",null,null);
        List<Emp> emps = dynamicSQLMapper.getEmpByWhere(emp);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //trim元素
    @Test
    public void testGetEmpByTrim(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(2,"lisi",null,null);
        List<Emp> emps = dynamicSQLMapper.getEmpByTrim(emp);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //set元素
    @Test
    public void testUpdateEmpBySet(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(4,"wanger",null,"男");
        dynamicSQLMapper.updateEmpBySet(emp);
        sqlSession.close();
    }

    //foreach元素迭代数组
    @Test
    public void testGetEmpByArray(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Integer[] ids = {1,2,3};
        List<Emp> emps = dynamicSQLMapper.getEmpByArray(ids);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //foreach元素迭代List
    @Test
    public void testGetEmpByList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(3);
        ids.add(4);
        List<Emp> emps = dynamicSQLMapper.getEmpByList(ids);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //foreach元素迭代List
    @Test
    public void testGetEmpByMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("ids",ids);
        conditionMap.put("gender","男");
        List<Emp> emps = dynamicSQLMapper.getEmpByMap(conditionMap);
        emps.forEach(System.out::println);
        sqlSession.close();
    }

    //sql元素
    @Test
    public void testGetEmpBySql(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = dynamicSQLMapper.getEmpBySql("a");
        emps.forEach(System.out::println);
        sqlSession.close();
    }

}
