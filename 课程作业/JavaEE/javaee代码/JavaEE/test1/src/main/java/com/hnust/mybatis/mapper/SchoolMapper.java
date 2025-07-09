package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: SchoolMapper
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 21:55
 */
public interface SchoolMapper {
    School selectCourseBySchoolName(@Param("sname") String sname);
    //5. 输出所有的学院开设的课程信息。
    List<School> selectAllSchools();

}
