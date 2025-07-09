package com.hnust.mapper;

import com.hnust.pojo.Course;
import com.hnust.pojo.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: SchoolMapper
 * Package: com.hnust.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/5 - 14:40
 */
public interface SchoolMapper {
    List<School> getAllSchool();

    School getSchoolBySid(@Param("sid") Integer sid);

    List<Course> getCourseBySid(@Param("schools") Integer schools);
}
