package com.dq.shop;

import com.dq.common.POJO.ResultBean;
import com.project.entity.TProduct;

/**
 * @author DuQian
 * @Date 2019/3/15
 */
public interface ItemService {

    ResultBean createHtml(TProduct product);

    ResultBean batchHtml(TProduct[] products);
}
