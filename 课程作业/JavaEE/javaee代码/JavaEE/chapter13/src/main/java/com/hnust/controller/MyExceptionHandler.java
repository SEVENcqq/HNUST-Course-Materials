package com.hnust.controller;

import com.hnust.exception.MyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * ClassName: MyExceptionHandler
 * Package: com.hnust.controller
 * Description: chapter13.1.2
 * 自定义异常处理器的优先级高于简单异常处理器，当有异常出现，会执行自定义异常处理器的规则
 * 原先简单异常处理器的规则会被覆盖，想要经过简单异常处理器，则把自定义异常处理器注释掉
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 16:25
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

           /*
            1.requset 当前的HTTP request
            2.response 当前的HTTP response
            3.handler 正在执行的Handler
            4.ex handler 执行时抛出的exception
            5.返回一个ModelAndView
            */

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 定义异常信息
        String msg;
        // 如果是自定义异常，将异常信息直接返回
        if (ex instanceof MyException){
            msg = ex.getMessage();
        } else {
            // 如果是系统异常，从堆栈中获取异常信息
            Writer out = new StringWriter();
            PrintWriter s = new PrintWriter(out);
            ex.printStackTrace(s);
            // 系统真实异常信息，可以以邮箱和短信等形式发送给开发人员
            String sysMsg = out.toString();
            msg = "网络异常！";
        }
        // 返回错误页面，给用户友好页面显示错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",msg);
        modelAndView.setViewName("error.jsp");
        return modelAndView;
    }
}
