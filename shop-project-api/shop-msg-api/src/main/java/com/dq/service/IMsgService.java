package com.dq.service;

import com.dq.common.POJO.MsgResultBean;

/**
 * @author DuQian
 * @Date 2019/3/19
 */
public interface IMsgService {
    void sendMsg(String templateParam ,String phoneNumbers);
}
