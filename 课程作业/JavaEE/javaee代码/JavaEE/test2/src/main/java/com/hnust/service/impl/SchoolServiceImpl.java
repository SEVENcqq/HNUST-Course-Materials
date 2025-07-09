package com.hnust.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnust.mapper.SchoolMapper;
import com.hnust.pojo.Course;
import com.hnust.pojo.School;
import com.hnust.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SchoolServiceImpl
 * Package: com.hnust.service.impl
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/5 - 14:44
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public PageInfo<School> getAllSchool(Integer spageNum) {
        PageHelper.startPage(spageNum, 7);
        List<School> schools = schoolMapper.getAllSchool();
        PageInfo<School> spage = new PageInfo<>(schools, 2);
        return spage;
    }

    @Override
    public PageInfo<Course> getCourseBySid(Integer cpageNum, Integer sid) {
        PageHelper.startPage(cpageNum, 5);
        /*School school = schoolMapper.getSchoolBySid(sid);
        List<Course> courses = school.getCourses();*/
        List<Course> courses = schoolMapper.getCourseBySid(sid);
        PageInfo<Course> cpage = new PageInfo<>(courses, 2);
        return cpage;
    }
}
