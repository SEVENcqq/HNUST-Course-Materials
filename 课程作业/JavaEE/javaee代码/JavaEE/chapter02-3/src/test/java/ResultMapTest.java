import com.hnust.mybatis.mapper.DeptMapper;
import com.hnust.mybatis.mapper.EmpMapper;
import com.hnust.mybatis.pojo.Dept;
import com.hnust.mybatis.pojo.Emp;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * ClassName: ResultMapTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 16:19
 */
public class ResultMapTest {
    @Test
    public void testGetEmpByEmpId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        System.out.println(empMapper.getEmpByEmpId(1));
        sqlSession.close();
    }

    @Test
    public void testGetEmpAndDeptByEmpId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        System.out.println(empMapper.getEmpAndDeptByEmpId(1));
        sqlSession.close();
    }

    @Test
    public void testGetEmpAndDeptByStep(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpAndDeptByStepOne((2));
        System.out.println(emp.getEmpName());
        /*System.out.println(emp);*/
        sqlSession.close();
    }

    @Test
    public void testGetDeptAndEmpByDeptId(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        System.out.println(deptMapper.getDeptAndEmpByDeptId(2));
        sqlSession.close();
    }

    @Test
    public void testGetDeptAndEmpByStep(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptAndEmpByStepOne(1);
        System.out.println(dept);
        sqlSession.close();
    }
}
