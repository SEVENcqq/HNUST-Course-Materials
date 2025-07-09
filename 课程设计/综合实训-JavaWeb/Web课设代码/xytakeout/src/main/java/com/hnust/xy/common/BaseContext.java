package com.hnust.xy.common;

/**
 * ClassName: BaseContext
 * Package: com.hnust.xy.common
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 17:46
 */

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
