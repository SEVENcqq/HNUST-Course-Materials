package com.hnust.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName: LoginInterceptor
 * Package: com.hnust.interceptor
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 17:58
 */
// 每次跳转页面都会经过此拦截器作判断
public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的路径
        String uri = request.getRequestURI();
        // 对用户登录的相关请求进行判断
        if(uri.indexOf("/login") >= 0 || uri.indexOf("/register") >= 0 || uri.indexOf("/toLogin") >= 0 || uri.indexOf("/toRegister") >= 0){
            return true;
        }
        HttpSession session = request.getSession();
        // 如果用户是已登录状态，放行
        if (session.getAttribute("user") != null){
            return true;
        }
        // 其他情况都直接跳转到登录页面
        request.setAttribute("msg","您还没登录，请先登录！");
        //request.getRequestDispatcher("/WEB-INF/templates/login.html").forward(request,response);
        request.getRequestDispatcher("/toLogin").forward(request,response);
        return false;
    }
}
