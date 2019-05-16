package com.dq.shop;

import com.dq.common.POJO.PageInfo;
import com.dq.common.POJO.ResultBean;
import com.project.entity.TProduct;

/**
 * @author DuQian
 * @Date 2019/3/14
 */
public interface ISearchService {

    PageInfo page(String keyWord, Integer indexNum, Integer pageSize);

    ResultBean initAllData();

    ResultBean updateData(TProduct product);
}
