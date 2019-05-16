package com.dq.common.POJO;

import java.io.Serializable;

public class MultiResultBean implements Serializable{
    //返回的状态码
    private String errno;
    private String[] data;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
