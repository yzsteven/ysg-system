package com.api.model;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
public class Response {

    private int retCode;

    private String retMsg;

    private Object retValue;

    public Response(int retCode,String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Response(int retCode,String retMsg, Object retValue){
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retValue = retValue;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getRetValue() {
        return retValue;
    }

    public void setRetValue(Object retValue) {
        this.retValue = retValue;
    }
}
