package com.hnust.mybatis.mapper;

import com.hnust.mybatis.pojo.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: SchoolMapperPlus
 * Package: com.hnust.mybatis.mapper
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/25 - 23:12
 */
public interface SchoolMapperPlus {
    @Select("select * from s_school where school_name = #{sname}")
    @Results({
        @Result(id = true, column = "sid", property = "sid"),
        @Result(column = "school_name", property = "sname"),
        @Result(column = "cid", property = "courses", javaType = List.class,
                many = @Many(select = "com.hnust.mybatis.mapper.CourseMapperPlus.selectCourseByStepAndId"))
    })
    School selectCourseBySchoolName(@Param("sname") String sname);
    //5. 输出所有的学院开设的课程信息。
    @Select("select * from s_school")
    @Results({
            @Result(id = true, column = "sid", property = "sid"),
            @Result(column = "school_name", property = "sname"),
            @Result(column = "cid", property = "courses",
                    many = @Many(select = "com.hnust.mybatis.mapper.CourseMapperPlus.selectAllByStep"))
    })
    List<School> selectAllSchools();
}
