package com.hnust.controller;

import com.hnust.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ClassName: ExceptionController
 * Package: com.hnust.controller
 * Description: chapter13.1
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/29 - 23:17
 */
@Controller
public class ExceptionController {
    // 抛出空指针异常
    @RequestMapping("/showNullPointer")
    public void showNullPointer(){
        ArrayList<Object> list = null;
        System.out.println(list.get(2));
    }

    // 抛出IO异常
    @RequestMapping("/showIOException")
    public void showIOException() throws IOException {
        FileInputStream in = new FileInputStream("JavaWeb.xml");
    }

    // 抛出算术异常
    @RequestMapping("/showArithmetic")
    public void showArithmetic(){
        int c = 1/0;
    }

    // 抛出运行时异常
    @RequestMapping("/showRunTime")
    public void showRunTime() throws RuntimeException {
        throw new RuntimeException();
    }

    // 自定义异常处理器
    @RequestMapping("/addData")
    public void addData() throws MyException {
        throw new MyException("新增数据异常！");
    }
}
