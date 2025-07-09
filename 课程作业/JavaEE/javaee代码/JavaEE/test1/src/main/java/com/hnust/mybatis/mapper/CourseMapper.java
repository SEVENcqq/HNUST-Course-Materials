package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.Course;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: CourseMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 21:55
 */
public interface CourseMapper {

    //1. 查询 id=2 的课程信息；
    Course selectCourseById(Integer cid);
    // 3. 将 id=4 这⻔课程的课时数修改为 32+8=40；
    void updateCourseById(@Param("cid") Integer cid, @Param("hours") Integer hours);
    //4. 插⼊⼀条新的课程记录： names=”⼤数据存储“，hours=32，schools =1；
    void insertCourse(Course course);
}
