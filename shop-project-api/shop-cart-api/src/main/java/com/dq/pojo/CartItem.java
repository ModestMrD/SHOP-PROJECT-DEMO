package com.dq.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DuQian
 * @Date 2019/3/25
 */
public class CartItem implements Serializable{

    private Long productId;

    private Integer count;

    private Date updateDate;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public CartItem() {
    }

    public CartItem(Long productId, Integer count, Date updateDate) {
        this.productId = productId;
        this.count = count;
        this.updateDate = updateDate;
    }
}
