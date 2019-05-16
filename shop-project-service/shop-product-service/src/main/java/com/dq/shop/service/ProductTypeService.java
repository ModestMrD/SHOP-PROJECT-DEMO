package com.dq.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.service.BaseServiceImpl;
import com.dq.common.service.IBaseDao;
import com.dq.common.service.IProductService;
import com.dq.common.service.IProductTypeService;
import com.dq.common.vo.ProductVO;
import com.dq.project.mapper.TProductDescMapper;
import com.dq.project.mapper.TProductMapper;
import com.dq.project.mapper.TProductTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.entity.TProduct;
import com.project.entity.TProductDesc;
import com.project.entity.TProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Service
public class ProductTypeService extends BaseServiceImpl implements IProductTypeService {

    @Autowired
    TProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }

}
