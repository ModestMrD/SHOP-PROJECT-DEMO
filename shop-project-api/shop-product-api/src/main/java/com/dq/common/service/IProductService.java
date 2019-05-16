package com.dq.common.service;

import com.dq.common.vo.ProductVO;
import com.github.pagehelper.PageInfo;
import com.project.entity.TProduct;

/**
 * @author DuQian
 * @Date 2019/3/8
 */
public interface IProductService extends IBaseService{

    PageInfo page(Integer pageSize, Integer currentPage);

    TProduct add(ProductVO productVO);
}
