package com.project.p1.bean.vo;

import java.util.List;

public class OrdersByPageVO {
    private Integer total;
    private List<OrderInOrdersByPageVO> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrderInOrdersByPageVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInOrdersByPageVO> orders) {
        this.orders = orders;
    }

    public OrdersByPageVO() {
    }

    public OrdersByPageVO(Integer total, List<OrderInOrdersByPageVO> orders) {
        this.total = total;
        this.orders = orders;
    }
}
