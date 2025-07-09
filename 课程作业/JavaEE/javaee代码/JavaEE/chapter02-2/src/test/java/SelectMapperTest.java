import com.hnust.mybatis.mapper.SelectMapper;
import com.hnust.mybatis.pojo.User;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SelectMapperTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 12:35
 */
public class SelectMapperTest {

    /*一. 如果查询出的数据只有一条，可以通过
        1. 实体类对象接收
        2. List集合接收
        3. Map集合接收，结果`{password=123456, sex=男, id=1, age=23, username=admin}`
    二. 如果查询出的数据有多条，一定不能用实体类对象接收，会抛异常TooManyResultsException，可以通过
        1. 实体类类型的LIst集合接收
        2. Map类型的LIst集合接收
        3. 在mapper接口的方法上添加@MapKey注解*/

    //根据id查询用户信息
    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(selectMapper.getUserById(3));
        sqlSession.close();
    }

    //查询所有用户信息
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = selectMapper.getAllUser();
        users.forEach(System.out::println);
        sqlSession.close();
    }

    //查询数量
    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(selectMapper.getCount());
        sqlSession.close();
    }

    //查询结果未含具体实体类(如最高薪资)
    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(selectMapper.getUserByIdToMap(4));
        //结果展示：{password=123456, gender=男, id=2, age=23, email=12345@qq.com, username=admin}
        //本质：将每个对象的数据以键值对的形式存放在map集合中
        sqlSession.close();
    }

    //查询所有用户信息为map集合,方法一
    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<Map<String , Object>> users = selectMapper.getAllUserToMap();
        users.forEach(System.out::println);
        /*{password=1234567, gender=女, id=1, age=20, email=1302466947@qq.com, username=cqq}
        {password=123456, gender=男, id=2, age=23, email=12345@qq.com, username=admin}
        {password=123, gender=男, id=3, age=21, email=123@qq.com, username=张三}
        {password=NULL, gender=男, id=4, age=25, email=NULL, username=李四}*/
        sqlSession.close();
    }

    //查询所有用户信息为map集合,方法二
    @Test
    public void testGetAllUserToMap2(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Map<String , Object> users = selectMapper.getAllUserToMap2();
        System.out.println(users);
           /*{1={password=1234567, gender=女, id=1, age=20, email=1302466947@qq.com, username=cqq},
            2={password=123456, gender=男, id=2, age=23, email=12345@qq.com, username=admin},
            3={password=123, gender=男, id=3, age=21, email=123@qq.com, username=张三},
            4={password=NULL, gender=男, id=4, age=25, email=NULL, username=李四}}*/
        sqlSession.close();
    }
}
