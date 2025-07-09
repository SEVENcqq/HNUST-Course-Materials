package com.hnust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: Controller
 * Package: com.hnust.controller
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/22 - 22:14
 */
@Controller
@RequestMapping("/test")
public class RequestMappingController {

    @RequestMapping({"/success", "/a"})
    public String success(){
        return "success";
    }

    @RequestMapping(value = {"/success2", "/a2"},method = RequestMethod.GET)
    public String success2(){
        return "success2";
    }

    @RequestMapping(value = {"/success3"},method = {RequestMethod.POST, RequestMethod.GET})
    public String success3(){
        return "success3";
    }

    @GetMapping(value = {"/success4"} , headers = {"referer"})
    public String success4(){
        return "success4";
    }

    @RequestMapping (value = {"/success5"} , params = {"id","!username"})
    public String success5(){
        return "success5";
    }

    @RequestMapping (value = {"/a?a/ant","/ant/*","/**/ant"})
    public String success6(){
        return "success";
    }

    @RequestMapping (value = {"/hhh/{id}/{name}"})
    public String success7(@PathVariable("id") Integer id,@PathVariable("name") String name){
        System.out.println("id=" + id + ", name=" + name);
        return "success";
    }

}
