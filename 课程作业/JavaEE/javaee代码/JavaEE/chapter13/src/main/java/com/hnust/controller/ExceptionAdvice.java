package com.hnust.controller;

import com.hnust.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * ClassName: ExceptionAdvice
 * Package: com.hnust.controller
 * Description: chapter13.1.3 异常处理注解
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 16:44
 */
@ControllerAdvice
public class ExceptionAdvice {

    // 处理MyException类型的异常
    @ExceptionHandler(MyException.class)
    public ModelAndView doMyException(MyException ex) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.jsp");
        modelAndView.addObject("msg",ex.getMessage());
        return modelAndView;
    }

    // 处理NullPointerException类型的异常
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView NullPointerException(NullPointerException ex) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.jsp");
        modelAndView.addObject("msg","空指针异常！");
        return modelAndView;
    }

    // 处理IOException类型的异常
    @ExceptionHandler(IOException.class)
    public ModelAndView IOException(IOException ex) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.jsp");
        modelAndView.addObject("msg","IO异常");
        return modelAndView;
    }

    // 处理ArithmeticException类型的异常
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView ArithmeticException(ArithmeticException ex) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.jsp");
        modelAndView.addObject("msg","算术异常！");
        return modelAndView;
    }

    // 处理Exception类型的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView doOtherException(Exception ex) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.jsp");
        modelAndView.addObject("msg","网络异常2！");
        return modelAndView;
    }
}
