package com.dq.service;

import com.dq.common.POJO.ResultBean;

/**
 * @author DuQian
 * @Date 2019/3/25
 */
public interface ICartService {
    //添加
    ResultBean addCart(String key,Long productId,Integer count);
    //更新
    ResultBean updateCart(String key,Long productId,Integer count);
    //删除
    ResultBean deleteCart(String key,Long productId);
    //查询
    ResultBean cartList(String key);
}
