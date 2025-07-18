package com.hnust.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: CodeUtil
 * Package: com.hnust.utils
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/4/12 - 17:08
 */
public class CodeUtil {
    /**
     * 将获取到的前端参数转为string类型
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request,String key) {
        try {
            String result = request.getParameter(key);
            if(result != null) {
                result = result.trim();
            }
            if("".equals(result)) {
                result = null;
            }
            return result;
        }catch(Exception e) {
            return null;
        }
    }
    /**
     * 验证码校验
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入的验证码
        String verifyCodeActual = CodeUtil.getString(request, "code");
        // 这里可以设置成允许匹配大小写
        // !verifyCodeActual.equals(verifyCodeExpected
        if(verifyCodeActual == null ||verifyCodeActual.compareToIgnoreCase(verifyCodeExpected) != 0) {
            return false;
        }
        return true;
    }
}

