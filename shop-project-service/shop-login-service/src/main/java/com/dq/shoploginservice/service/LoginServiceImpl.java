package com.dq.shoploginservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.POJO.ResultBean;
import com.dq.project.mapper.TUserMapper;
import com.dq.service.ILoginService;
import com.project.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author DuQian
 * @Date 2019/3/23
 */
@Service
@Component
public class LoginServiceImpl implements ILoginService{

    @Autowired
    private TUserMapper userMapper;

    @Override
    public TUser login(TUser user) {
        return userMapper.login(user);
    }
}
