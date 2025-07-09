package com.hnust.spring.test;

import com.hnust.spring.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName: JdbcTemplateTest
 * Package: com.hnust.spring.test
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/1 - 23:09
 */
// 指定当前测试类在spring的测试环境中执行，此时就可以通过注入的方式直接获取ioc容器中的bean
@RunWith(SpringJUnit4ClassRunner.class)
// 设置spring测试环境的配置文件
@ContextConfiguration("classpath:spring-jdbc.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate; // 这一步类似于从ioc容器中获取bean

    @Test
    public void testInsert(){
        String sql = "insert into t_user values(null,?,?,?,?,?)";
        jdbcTemplate.update(sql, "zhaoliu", "12345", 28, "男", "55555@qq.com");
    }

    @Test
    public void testGetUserById(){
        String sql = "select * from t_user where id = ?";
        // BeanPropertyRowMapper<>() 字段名与实体类中的属性进行映射，默认的映射关系是字段名与属性名一致就进行赋值
        // 查询出的结果放在User对象中
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),1);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser(){
        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        users.forEach(System.out::println);
    }

    @Test
    public void testGetCount(){
        String sql = "select count(*) from t_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
