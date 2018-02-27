package com.api.model;

/**
 * 返回码枚举
 * Created by zhou_yanga on 2017/9/1.
 */
public enum ResultCode {

    SUCCESS(200, "操作成功"), SYSTENERROR(500, "系统错误"), PARAMETERERROR(400, "请求参数错误");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
