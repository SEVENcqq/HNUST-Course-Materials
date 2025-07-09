package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CourseMapperPlus
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/25 - 23:11
 */
public interface CourseMapperPlus {
    //1. 查询 id=2 的课程信息；
    @Select("select * from c_course where cid = #{cid}")
    Course selectCourseById(Integer cid);
    // 3. 将 id=4 这⻔课程的课时数修改为 32+8=40；
    @Update("update c_course set hours = #{hours} where cid = #{cid}")
    void updateCourseById(@Param("cid") Integer cid, @Param("hours") Integer hours);
    //4. 插⼊⼀条新的课程记录： names=”⼤数据存储“，hours=32，schools =1；
    @Insert("insert into c_course values(#{cid}, #{cname}, #{hours}, #{schools}, #{cpath}, #{cinf})")
    void insertCourse(Course course);

    @Select("select * from c_course where cid = #{cid} ")
    @Results({
        @Result(id = true, column = "cid", property = "cid"),
        @Result(column = "cname", property = "cname"),
        @Result(column = "hours", property = "hours"),
        @Result(column = "schools", property = "schools")
    })
    List<Course> selectCourseByStepAndId(Integer cid);//@Param("cid") Integer cid



    @Select("select * from c_course where cid = #{cid}")
    @Results({
            @Result(id = true, column = "cid", property = "cid"),
            @Result(column = "cname", property = "cname"),
            @Result(column = "hours", property = "hours"),
            @Result(column = "schools", property = "schools")
    })
    List<Course> selectAllByStep(@Param("cid") Integer cid);
}
