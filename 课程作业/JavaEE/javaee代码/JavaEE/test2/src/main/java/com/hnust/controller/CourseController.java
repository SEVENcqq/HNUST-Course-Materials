package com.hnust.controller;

import com.github.pagehelper.PageInfo;
import com.hnust.pojo.Cinfo;
import com.hnust.pojo.Course;
import com.hnust.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: CourseController
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/3 - 20:09
 */


@Controller
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*@RequestMapping("/searchCourse/{pageNum}/{username}/{spageNum}")
    public String toSearchCourse(@PathVariable("pageNum") Integer pageNum, @PathVariable("username") String username,
                               @PathVariable("spageNum") Integer spageNum, Course course, Model model){
        *//*System.out.println("---------------------------------------------------");
        System.out.println(course);*//*
        PageInfo<Course> page = courseService.searchCourse(pageNum,course);
        model.addAttribute("spageNum",spageNum);
        model.addAttribute("username",username);
        model.addAttribute("page",page);
        return "course_search";
    }*/

    // 获得课程信息列表
    @RequestMapping("/searchCourse/{pageNum}/{username}/{spageNum}")
    public String searchCourse(@PathVariable("pageNum") Integer pageNum, @PathVariable("username") String username,
                               @PathVariable("spageNum") Integer spageNum, Course course, Model model){
        /*System.out.println("---------------------------------------------------");
        System.out.println(course);*/
        PageInfo<Course> page = courseService.searchCourse(pageNum,course);
        model.addAttribute("spageNum",spageNum);
        model.addAttribute("username",username);
        model.addAttribute("page",page);
        return "course_search";
    }

    // 课程添加
    @GetMapping("/toAdd/{cpages}/{cpageNum}/{sid}/{spageNum}/{username}")
    public String toAdd(@PathVariable("cpages") Integer cpages, @PathVariable("cpageNum") Integer cpageNum,
                        @PathVariable("sid") Integer sid, @PathVariable("spageNum") Integer spageNum,
                        @PathVariable("username") String username, Model model){
        model.addAttribute("cpages", cpages);
        model.addAttribute("cpageNum", cpageNum);
        model.addAttribute("spageNum", spageNum);
        model.addAttribute("sid", sid);
        model.addAttribute("username", username);
        return "course_add";
    }

    @PostMapping("/addCourse/{cpages}/{cpageNum}/{sid}/{spageNum}/{username}")
    public String addCourse(@PathVariable("cpages") Integer cpages, @PathVariable("cpageNum") Integer cpageNum,
                            @PathVariable("sid") Integer sid, @PathVariable("spageNum") Integer spageNum,
                            @PathVariable("username") String username, Course course, Model model){

        boolean judge;
        boolean flag = false;
        if(course.getCname() != null && course.getHours() != null && course.getSchools() != null){
            judge = true;
        } else {
            judge = false;
        }
        if(judge==true){
            flag = courseService.addCourse(course);
        }
        /*judge == true&&*/
        if(judge == true&&flag == true){
            return "redirect:/getCourseBySid/" + spageNum + '/' + sid + '/' + cpages + '/' + username;
        } else {
            if(judge == false){
                model.addAttribute("msg", "数据不能为空，添加失败！");
                model.addAttribute("course", course);
            } else {
                model.addAttribute("msg", "已有该课程，添加失败！");
            }
            model.addAttribute("pageNum", cpageNum);
            return "course_add";
        }
    }

    // 课程修改
    @GetMapping("/toUpdate/{cid}/{cpageNum}/{sid}/{spageNum}/{username}")
    public String toUpdate(@PathVariable("cid") Integer cid, @PathVariable("cpageNum") Integer cpageNum,
                           @PathVariable("sid") Integer sid, @PathVariable("spageNum") Integer spageNum,
                           @PathVariable("username") String username, Model model){
        Course course = courseService.getCourseByCid(cid);
        model.addAttribute("cpageNum", cpageNum);
        model.addAttribute("course", course);
        model.addAttribute("cn", course.getCname());
        model.addAttribute("spageNum", spageNum);
        model.addAttribute("sid", sid);
        model.addAttribute("username", username);
        return "course_update";
    }

    @PostMapping("/updateCourse/{cpageNum}/{cn}/{sid}/{spageNum}/{username}")
    public String updateCourse(@PathVariable("cpageNum") Integer cpageNum, @PathVariable("cn") String cn,
                               @PathVariable("sid") Integer sid, @PathVariable("spageNum") Integer spageNum,
                               @PathVariable("username") String username, Course course, Model model, HttpServletRequest request){
        boolean flag = courseService.updateCourse(course,cn);
        if(flag == true){
            return "redirect:/getCourseBySid/" + spageNum + '/' + sid + '/' + cpageNum + '/' + username;
        } else {
            model.addAttribute("msg", "已有该课程，修改失败！");
            model.addAttribute("cn", cn);

            model.addAttribute("cpath", request.getParameter("cpath"));
            model.addAttribute("cinf", request.getParameter("cinf"));
            return "course_update";
        }
    }

    // 课程删除
    @GetMapping("/deleteCourseByCid/{cid}/{cpageNum}/{sid}/{spageNum}/{username}")
    public String deleteCourseByCid(@PathVariable("cid") Integer cid, @PathVariable("cpageNum") Integer cpageNum,
                                    @PathVariable("sid") Integer sid, @PathVariable("spageNum") Integer spageNum,
                                    @PathVariable("username") String username){
        courseService.deleteCourseByCid(cid);
        return "redirect:/getCourseBySid/" + spageNum + '/' + sid + '/' + cpageNum + '/' + username;
    }

    @GetMapping("/courseInfo/{cid}/{cname}")
    public String courseInfo(@PathVariable("cid") Integer cid, @PathVariable("cname") String cname, Model model){
        Cinfo cinfo = courseService.getCinfoByCid(cid);
        model.addAttribute("cname", cname);
        model.addAttribute("cinfo", cinfo);
        return "course_info";
    }

    /*@RequestMapping("/courseImgUp/{cname}")*/
    @RequestMapping("/courseImgUp/{cid}")
    public String courseImgUp(MultipartFile photo, Course course, Model model, HttpServletRequest request) throws IOException {
    /*public String courseImgUp(MultipartFile photo, @PathVariable("cname") String cname, Cinfo cinfo, Model model) throws IOException {*/
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        /*String photoPath = "D:/CompileSoftware/JAVA/IDEA/IDEAworkspace/JavaEE/test2/src/main/webapp/img/course";*/
        String photoPath = "D:/StudyMaterial/DataStorage/course";//这里是图片放置在磁盘的位置
        String finalPath = photoPath + File.separator + fileName;
        photo.transferTo(new File(finalPath));
        Integer cid = course.getCid();
        course.setCpath(fileName);
        courseService.updateCourseImgByCid(course,cid);
        model.addAttribute("course",course);

        model.addAttribute("spageNum", request.getParameter("spageNum"));
        model.addAttribute("cpageNum", request.getParameter("cpageNum"));
        model.addAttribute("sid", request.getParameter("sid"));
        model.addAttribute("username", request.getParameter("username"));

        return "course_update";


        /*cinfo.setCpath(fileName);
        courseService.saveCinfo(cinfo);
        model.addAttribute("cinfo",cinfo);
        model.addAttribute("cname",cname);
        return "course_info";*/
    }

}
