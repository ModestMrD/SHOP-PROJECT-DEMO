package com.dq.miaosha.pojo;

import java.io.Serializable;

/**
 * @author DuQian
 * @Date 2019/3/29
 */
public class ResultBean implements Serializable {

    private String statusCode;

    private String msg;

    public static ResultBean success(String msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode("200");
        resultBean.setMsg(msg);
        return resultBean;
    }

    public static ResultBean error(String msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode("500");
        resultBean.setMsg(msg);
        return resultBean;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
