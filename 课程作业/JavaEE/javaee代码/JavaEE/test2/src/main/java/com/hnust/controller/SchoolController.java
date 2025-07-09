package com.hnust.controller;

import com.github.pagehelper.PageInfo;
import com.hnust.pojo.Course;
import com.hnust.pojo.School;
import com.hnust.service.CourseService;
import com.hnust.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: SchoolController
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/5 - 14:37
 */
@Controller
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllSchool/{spageNum}/{username}")
    public String getAllSchool(@PathVariable("spageNum") Integer spageNum, @PathVariable("username") String username, Model model){
        PageInfo<School> spage = schoolService.getAllSchool(spageNum);
        model.addAttribute("spage", spage);
        model.addAttribute("username", username);
        return "school_list";
    }

    @GetMapping("/getCourseBySid/{spageNum}/{sid}/{cpageNum}/{username}")
    public String getCourseBySid(@PathVariable("spageNum") Integer spageNum, @PathVariable("sid") Integer sid,
                                 @PathVariable("cpageNum") Integer cpageNum, @PathVariable("username") String username, Model model){
        PageInfo<Course> cpage = schoolService.getCourseBySid(cpageNum, sid);
        model.addAttribute("cpage",cpage);
        model.addAttribute("spageNum",spageNum);
        model.addAttribute("sid", sid);
        model.addAttribute("cpageNum", cpageNum);
        model.addAttribute("username", username);

        return "course_list";
    }

}
