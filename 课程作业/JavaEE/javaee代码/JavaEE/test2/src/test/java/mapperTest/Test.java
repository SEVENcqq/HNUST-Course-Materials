package mapperTest;

import com.hnust.mapper.CourseMapper;
import com.hnust.mapper.SchoolMapper;
import com.hnust.mapper.UserMapper;
import com.hnust.pojo.Course;
import com.hnust.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName: Test
 * Package: mapperTest
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/4 - 23:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 设置spring测试环境的配置文件
@ContextConfiguration("classpath:spring.xml")
public class Test {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private UserMapper userMapper;


    @org.junit.Test
    public void test(){
        /*Course course = courseMapper.getCourseByCname("数据库");
        System.out.println(course);*/
        /*Cinfo cinfo = courseMapper.getCinfoByCid(1);
        System.out.println(cinfo);*/
        Course course = new Course();
        course.setCid(9);
        course.setHours(64);
        List<Course> courses = courseMapper.searchCourse(course);
        System.out.println("----------------------------------------------------");
        courses.forEach(System.out::println);
    }

    @org.junit.Test
    public void testSchoolMapper(){
        /*School school = schoolMapper.getSchoolBySid(1);
        List<Course> courses = school.getCourses();
        System.out.println(courses);*/

        List<Course> courses = schoolMapper.getCourseBySid(1);
        courses.forEach(System.out::println);
    }

    @org.junit.Test
    public void testUserMapper(){

        User user = new User();
        /*user.setPassword("7777777");
        user.setEmail("1302466947@qq.com");
        user.setUsername("lisi");*/
        user.setPassword("123456");
        user.setEmail("lisi@qq.com");
        user.setUsername("lisi");
        userMapper.saveUser(user);
    }

}
