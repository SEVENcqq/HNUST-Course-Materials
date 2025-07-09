package com.hnust.service;

import com.github.pagehelper.PageInfo;
import com.hnust.pojo.Course;
import com.hnust.pojo.School;

/**
 * ClassName: SchoolService
 * Package: com.hnust.service
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/5 - 14:44
 */
public interface SchoolService {
    PageInfo<School> getAllSchool(Integer spageNum);

    PageInfo<Course> getCourseBySid(Integer cpageNum, Integer sid);
}
