package com.dq.common.POJO;

import java.io.Serializable;

public class ResultBean<T> implements Serializable{
    //返回的状态码
    private Integer statusCode;
    //当成功之后，返回的数据
    private T data;
    //当失败之后，返回的错误信息
    private String msg;

    public ResultBean(Integer statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResultBean() {
    }

    public static ResultBean success(String data){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean error(String msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(500);
        resultBean.setData(msg);
        return resultBean;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
