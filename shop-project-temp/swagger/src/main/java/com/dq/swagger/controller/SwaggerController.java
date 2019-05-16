package com.dq.swagger.controller;

import com.dq.swagger.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DuQian
 * @Date 2019/3/13
 */
@RestController
@RequestMapping("swagger")
public class SwaggerController {

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户对象user", required = true, dataType = "User")
    @RequestMapping(value="add", method= RequestMethod.GET)
    public String postUser(@RequestBody User user) {
        System.out.println(user);
        return "success";
    }

}
