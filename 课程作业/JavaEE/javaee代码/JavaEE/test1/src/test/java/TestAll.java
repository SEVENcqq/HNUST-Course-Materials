import com.hnust.mybatis.mapper.CourseMapper;
import com.hnust.mybatis.mapper.SchoolMapper;
import com.hnust.mybatis.pojo.Course;
import com.hnust.mybatis.pojo.School;
import com.hnust.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: TestAll
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/23 - 8:26
 */

/*注意：建表的时候一定不要让主键同名*/
public class TestAll {

    private CourseMapper courseMapper;
    private SchoolMapper schoolMapper;
    private SqlSession sqlSession;
    @Before
    public void setUp(){
        sqlSession = SqlSessionUtil.getSqlSession();
        courseMapper = sqlSession.getMapper(CourseMapper.class);
        schoolMapper = sqlSession.getMapper(SchoolMapper.class);
    }

    @After
    public void release(){
        sqlSession.close();
    }

    // 1. 查询 id=2 的课程信息；
    @Test
    public void testSelectCourseById(){
        System.out.println(courseMapper.selectCourseById(2));
    }

    // 2. 查询出所有计算机学院开设的课程信息 ；
    @Test
    public void testSelectCourseBySchoolName(){
        System.out.println(schoolMapper.selectCourseBySchoolName("计算机科学与工程学院"));
    }

    // 3. 将 id=4 这⻔课程的课时数修改为 32+8=40；
    @Test
    public void testUpdateCourseById(){
        courseMapper.updateCourseById(4,40);
    }

    // 4. 插⼊⼀条新的课程记录： names=”⼤数据存储“，hours=32，schools =1；
    @Test
    public void testInsertCourse(){
        Course course = new Course(null,"大数据存储",32,1,null,null);
        courseMapper.insertCourse(course);
    }

    // 5. 输出所有的学院开设的课程信息。
    @Test
    public void testSelectAllSchools(){
        List<School> schools = schoolMapper.selectAllSchools();
        schools.forEach(System.out::println);
    }

}
