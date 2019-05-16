package com.dq.shoploginbackground.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dq.common.POJO.ResultBean;
import com.dq.service.ILoginService;
import com.project.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author DuQian
 * @Date 2019/3/23
 */
@Controller
@RequestMapping("loginController")
public class LoginController {

    @Reference
    private ILoginService loginService;

    @Autowired
    private RedisTemplate template;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(TUser user,HttpServletResponse response){
        TUser tUser = loginService.login(user);
        if (tUser==null){
            return ResultBean.error("未找到用户");
        }
        String key = "user_token";
        String uuid = UUID.randomUUID().toString();
        String cookieKey =key+uuid;
        //将uuid存入cookie
        Cookie cookie = new Cookie(cookieKey,uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        String redisKey = "user:token:"+uuid;
        template.opsForValue().set(redisKey,tUser);
        template.expire(redisKey,30, TimeUnit.MINUTES);
        return ResultBean.success("登录成功");
    }

    @RequestMapping("checkLogin")
    public ResultBean checkLogin(@CookieValue(name= "user_token",required = false) String uuid){
        String redisKey = "user:token:"+uuid;
        if (uuid==null){
            return ResultBean.error("用户未登录");
        }
        TUser user = (TUser) template.opsForValue().get(redisKey);
        if (user!=null){
            template.expire(user,30,TimeUnit.MINUTES);
            return ResultBean.success("用户已登录");
        }
        return ResultBean.error("用户未登录");
    }

    @RequestMapping("logout")
    @ResponseBody
    public ResultBean logout(@CookieValue(name= "user_token",required = false) String uuid , HttpServletResponse response){
        if (uuid==null){
            return ResultBean.error("不要闹");
        }
        String redisKey = "user:token:"+uuid;
        template.delete(redisKey);
        Cookie cookie = new Cookie("user_token","xx");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResultBean.success("注销成功");
    }
}
