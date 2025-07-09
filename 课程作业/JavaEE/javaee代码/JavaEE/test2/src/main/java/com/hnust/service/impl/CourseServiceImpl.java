package com.hnust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnust.mapper.CourseMapper;
import com.hnust.pojo.Cinfo;
import com.hnust.pojo.Course;
import com.hnust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CourseServiceImpl
 * Package: com.hnust.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 20:50
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public PageInfo<Course> getAllCourse(Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseMapper.getAllCourse();
        PageInfo<Course> page = new PageInfo<>(courses, 2);
        return page;
    }

    @Override
    public PageInfo<Course> searchCourse(Integer pageNum, Course course) {
        PageHelper.startPage(pageNum, 5);
        List<Course> courses = courseMapper.searchCourse(course);
        PageInfo<Course> page = new PageInfo<>(courses, 2);
        return page;
    }

    @Override
    public boolean addCourse(Course course) {
        String cname = course.getCname();
        Course c = courseMapper.getCourseByCname(cname);
        if(c != null){
            // 说明没有在数据库里已有输入的课程名
            return false;
        } else {
            // 说明该课程可以进行添加
            courseMapper.addCourse(course);
            return true;
        }
    }

    @Override
    public Course getCourseByCid(Integer cid) {
        return courseMapper.getCourseByCid(cid);
    }

    @Override
    public boolean updateCourse(Course course, String cn) {
        String cname = course.getCname();
        Course c = courseMapper.getCourseByCname(cname);
        if(cn.equals(cname) || c == null){
            // 说明该课程可以进行修改
            courseMapper.updateCourse(course);
            return true;
        } else {
            // 说明在数据库里已有输入的课程
            return false;
        }
    }

    @Override
    public void deleteCourseByCid(Integer cid) {
        courseMapper.deleteCourseByCid(cid);
    }

    @Override
    public void saveCinfo( Cinfo cinfo) {
        courseMapper.saveCinfo(cinfo);
    }

    @Override
    public Cinfo getCinfoByCid(Integer cid) {
        return courseMapper.getCinfoByCid(cid);
    }

    @Override
    public void updateCourseImgByCid(Course course,Integer cid) {
        String cpath = course.getCpath();
        String cinf = course.getCinf();
        courseMapper.updateCourseImgByCid(cpath, cinf, cid);
    }
}
