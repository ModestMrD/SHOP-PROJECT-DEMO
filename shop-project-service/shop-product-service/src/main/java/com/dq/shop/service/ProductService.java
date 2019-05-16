package com.dq.shop.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dq.common.service.BaseServiceImpl;
import com.dq.common.service.IBaseDao;
import com.dq.common.service.IProductService;
import com.dq.common.vo.ProductVO;
import com.dq.project.mapper.TProductDescMapper;
import com.dq.project.mapper.TProductMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.entity.TProduct;
import com.project.entity.TProductDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Service
public class ProductService extends BaseServiceImpl implements IProductService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductDescMapper productDescMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }

    @Override
    public PageInfo<TProduct> page(Integer pageSize, Integer pageIndex) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TProduct> list = productMapper.list();
        PageInfo<TProduct> pageInfo = new PageInfo<TProduct>(list,3);
        return pageInfo;
    }

    @Override
    public TProduct add(ProductVO productVO) {
        TProduct product = productVO.getProduct();
        //设置常规属性的值
        product.setFlag(true);
        product.setCreateTime(new Date());
        product.setUpdateTime(product.getCreateTime());
        product.setCreateUser(1L);
        product.setUpdateUser(product.getCreateUser());
        productMapper.insertSelective(product);
        TProductDesc desc = new TProductDesc();
        //设置TProductDesc的值
        desc.setProductId(product.getId());
        desc.setpDesc(productVO.getProductDesc());
        productDescMapper.insertSelective(desc);
        return product;
    }
}
