package com.api.model;

/**
 * 自定义插入修改失败异常
 * Created by zhou_yanga on 2018/4/17.
 */
public class IUFailException extends RuntimeException{

    public IUFailException(){
        super("insert or update fail exception");
    }
}
