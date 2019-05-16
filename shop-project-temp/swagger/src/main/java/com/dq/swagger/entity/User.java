package com.dq.swagger.entity;

import io.swagger.models.auth.In;

/**
 * @author DuQian
 * @Date 2019/3/13
 */
public class User {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
