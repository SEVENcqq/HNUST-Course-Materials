package com.dzhStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ZiHao Deng
 * @Created: 2023/4/8 12:57
 */
@Controller
public class IndexController {

    /**
     * 访问127.0.0.1:8080  或者127.0.0.1:8080/index 都可以访问
     * 跳转index.html
     * @return
     */
    @GetMapping(path = {"index","/",""})
    public String index(){
        return "index";
    }

}


