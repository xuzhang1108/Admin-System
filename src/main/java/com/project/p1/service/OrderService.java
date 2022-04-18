package com.project.p1.service;

import com.project.p1.bean.bo.OrderByPageBO;
import com.project.p1.bean.bo.OrderEditBO;
import com.project.p1.bean.vo.OrderEditVO;
import com.project.p1.bean.vo.OrdersByPageVO;

public interface OrderService {
    OrdersByPageVO ordersByPage(OrderByPageBO orderByPageBO);

    OrderEditVO order(int parseInt);

    void changeOrder(OrderEditBO orderEditBO);

    void deleteOrder(int parseInt);

}
