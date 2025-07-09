package com.hnust.service;

import com.github.pagehelper.PageInfo;
import com.hnust.pojo.Cinfo;
import com.hnust.pojo.Course;

/**
 * ClassName: CourseService
 * Package: com.hnust.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 20:49
 */
public interface CourseService {

    PageInfo<Course> getAllCourse(Integer pageNum);

    PageInfo<Course> searchCourse(Integer pageNum, Course course);

    boolean addCourse(Course course);

    Course getCourseByCid(Integer cid);

    boolean updateCourse(Course course, String cn);

    void deleteCourseByCid(Integer cid);

    void saveCinfo(Cinfo cinfo);

    Cinfo getCinfoByCid(Integer cid);

    void updateCourseImgByCid(Course course,Integer cid);
    
}
