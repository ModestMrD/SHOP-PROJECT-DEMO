package com.dq.miaosha.service.impl;

import com.dq.miaosha.entity.TProduct;
import com.dq.miaosha.service.IProductService;
import org.springframework.stereotype.Component;

/**
 * @author DuQian
 * @Date 2019/3/29
 */
@Component
public class ProductServiceImpl implements IProductService{
    @Override
    public TProduct getById(Long id) {
        return null;
    }

    @Override
    public boolean saleById(Long id) {
        return false;
    }
}
