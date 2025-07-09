package com.hnust.mapper;

import com.hnust.pojo.Cinfo;
import com.hnust.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: CourseMapper
 * Package: com.hnust.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/4 - 22:50
 */

/**
 * 查询所有课程
 * 根据课程id修改课程信息
 * 根据课程id删除课程信息
 * 添加课程信息
 */
public interface CourseMapper {

    // 查询所有课程
    List<Course> getAllCourse();

    // 根据课程提示语搜索相关课程信息
    List<Course> searchCourse(Course course);

    // 根据课程id获取课程信息
    Course getCourseByCid(@Param("cid") Integer cid);

    // 根据课程名获取课程信息
    Course getCourseByCname(@Param("cname") String cname);

    // 根据课程id修改课程信息
    void updateCourse(Course course);

    // 根据课程id删除课程信息
    void deleteCourseByCid(@Param("cid") Integer cid);

    // 添加课程信息
    void addCourse(Course course);


    // 保存课程其他信息
    void saveCinfo(Cinfo cinfo);

    // 获得Cinfo的数据信息
    Cinfo getCinfoByCid(Integer cid);

    // 修改课程图片路径和课程简介
    void updateCourseImgByCid(@Param("cpath") String cpath, @Param("cinf") String cinf, @Param("cid") Integer cid);


}
