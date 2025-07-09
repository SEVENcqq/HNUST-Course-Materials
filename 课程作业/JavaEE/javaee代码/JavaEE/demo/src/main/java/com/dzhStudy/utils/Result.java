package com.dzhStudy.utils;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ZiHao Deng
 * @Created: 2023/4/8 12:58
 */
@Data
public class Result {
    private String code;
    private String msg;
    private Object data;

    public Result(){

    }
    Result(String code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result success(Object data){
        return new Result("200","成功",data);
    }
}

