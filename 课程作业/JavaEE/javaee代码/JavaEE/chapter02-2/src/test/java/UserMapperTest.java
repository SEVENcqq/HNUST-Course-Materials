import com.hnust.mybatis.mapper.Usermapper;
import com.hnust.mybatis.pojo.User;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: mybatisTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * - MyBatis获取参数值的两种方式：${}和#{}
 * - ${}的本质就是字符串拼接，#{}的本质就是占位符赋值
 * - ${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，
 *   需要手动加单引号；但是#{}使用占位符赋值的方式拼接sql，
 *   此时为字符串类型或日期类型的字段进行赋值时，可以自动添加单引号
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 10:56
 */
public class UserMapperTest {

    //单个字面量类型的参数
    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Usermapper usermapper = sqlSession.getMapper(Usermapper.class);
        System.out.println(usermapper.getUserByUsername("cqq"));
        sqlSession.close();
    }

    //多个字面量类型的参数
    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Usermapper usermapper = sqlSession.getMapper(Usermapper.class);
        System.out.println(usermapper.checkLogin("cqq","1234567"));
        sqlSession.close();
    }

    //自定义参数，使用较少，了解即可
    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Usermapper usermapper = sqlSession.getMapper(Usermapper.class);
        Map<String , Object> map = new HashMap<>();
        map.put("username","cqq");//自定义参数名,缺点是手动添加参数
        map.put("password","1234567");
        System.out.println(usermapper.checkLoginByMap(map));
        sqlSession.close();
    }

    //插入用户信息
    /*若mapper接口中的方法参数为实体类对象时此时可以使用\${}和#{}，
    通过访问实体类对象中的属性名获取属性值，注意${}需要手动加单引号*/
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Usermapper usermapper = sqlSession.getMapper(Usermapper.class);
        User user = new User(3,"张三","123",21,"男","123@qq.com");
        usermapper.insertUser(user);
        sqlSession.close();
    }

    //重点掌握！
    /*可以通过@Param注解标识mapper接口中的方法参数，此时，会将这些参数放在map集合中
            1. 以@Param注解的value属性值为键，以参数为值；
            2. 以param1,param2...为键，以参数为值；*/
    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Usermapper usermapper = sqlSession.getMapper(Usermapper.class);
        System.out.println(usermapper.checkLoginByParam("cqq","1234567"));
        sqlSession.close();
    }

}
