package com.hnust.xy.common;

/**
 * ClassName: CustomException
 * Package: com.hnust.xy.common
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/23 - 20:04
 */

/**
 * 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
