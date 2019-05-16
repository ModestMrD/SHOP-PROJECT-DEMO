package com.dq.service;

import com.dq.common.POJO.ResultBean;
import com.project.entity.TUser;

/**
 * @author DuQian
 * @Date 2019/3/23
 */
public interface ILoginService {
    TUser login(TUser user);
}
