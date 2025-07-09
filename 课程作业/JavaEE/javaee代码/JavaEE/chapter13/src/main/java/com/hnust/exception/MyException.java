package com.hnust.exception;

/**
 * ClassName: MyException
 * Package: com.hnust.exception
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/3/30 - 16:20
 */
public class MyException extends Exception{
    private String message;

    public MyException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
