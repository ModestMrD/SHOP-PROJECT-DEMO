package com.dq.miaosha.service;

import com.dq.miaosha.entity.TProduct;
import org.springframework.stereotype.Service;

/**
 * @author DuQian
 * @Date 2019/3/29
 */
public interface IProductService {
    TProduct getById(Long id);

    boolean saleById(Long id);
}
