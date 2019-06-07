package com.example.demo.common.core.utils;

import java.io.Serializable;

/**
 * @Author ouyanggang
 * @Date 2019/6/7 - 07:52
 */
public abstract class BasePageModel implements Serializable {

    private Long page;

    private Long limit;

    private String orderField;

    private String order;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
