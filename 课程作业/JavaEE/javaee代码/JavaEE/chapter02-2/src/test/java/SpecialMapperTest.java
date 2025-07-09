import com.hnust.mybatis.mapper.SpecialMapper;
import com.hnust.mybatis.pojo.User;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * ClassName: SpecialMapperTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/14 - 13:14
 */
public class SpecialMapperTest {

    //模糊查询
    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper specialMapper = sqlSession.getMapper(SpecialMapper.class);
        List<User> users = specialMapper.getUserByLike("a");
        users.forEach(System.out::println);
        sqlSession.close();
    }

    //批量删除
    @Test
    public void testDeleteMoreUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper specialMapper = sqlSession.getMapper(SpecialMapper.class);
        specialMapper.deleteMoreUser("3,4");
        sqlSession.close();
    }

    //动态设置表名
    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper specialMapper = sqlSession.getMapper(SpecialMapper.class);
        List<User> list = specialMapper.getUserList("t_user");
        list.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialMapper specialMapper = sqlSession.getMapper(SpecialMapper.class);
        User user = new User(null,"mazi","11111",19,"男","11111@qq.com");
        specialMapper.insertUser(user);
        System.out.println(user);
        sqlSession.close();
    }

    public void testJDBC(){
        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection("","","");
            String sql = "insert into t_user values()";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeLargeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
