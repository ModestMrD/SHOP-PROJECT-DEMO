package com.dq.vo;

import com.project.entity.TProduct;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DuQian
 * @Date 2019/3/25
 */
public class CartItemVO implements Serializable{

    private TProduct product;

    private Integer count;

    private Date updateDate;

    public CartItemVO() {
    }

    public CartItemVO(TProduct product, Integer count, Date updateDate) {
        this.product = product;
        this.count = count;
        this.updateDate = updateDate;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
