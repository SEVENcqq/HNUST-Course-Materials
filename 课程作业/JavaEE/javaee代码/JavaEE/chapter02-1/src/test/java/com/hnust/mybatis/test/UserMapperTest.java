package com.hnust.mybatis.test;

import com.hnust.mybatis.mapper.UserMapper;
import com.hnust.mybatis.pojo.User;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassName: MybatisTest
 * Package: com.hnust.mybatis.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/13 - 19:51
 */
public class UserMapperTest {
    @Test
    public void testInsert() throws IOException {
        //获取核心配置的输入流
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory对象,类似于数据库
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取sql的会话对象SqlSession，是Mybatis提供的操作数据库的对象，类似于数据库中每操作一个表开放一个表
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动提交事务，默认的则不会自动提交事务

        //获取UserMapper的代理实现类对象，类似于获取数据库中的一张表
        //方法一：
        /*UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper接口中的接口方法，实现添加用户的功能，类似于实现表中的功能
        int result = userMapper.insertUser();*/

        //方法二：
        int result = sqlSession.insert("com.hnust.mybatis.mapper.UserMapper.insertUser");
        System.out.println("结果：" + result);
        //此处数据库里面还没有我们提交的数据，需要我们手动提交事务，默认是回滚的
        //sqlSession.commit();//提交事务
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser();
        sqlSession.close();
    }

    @Test
    public void TestDelete(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser();
        sqlSession.close();
    }

    @Test
    public void TestSelect(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById();
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void TestSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getAllUser();
        users.forEach(System.out::println);
        sqlSession.close();
    }
}
