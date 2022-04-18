package com.project.p1.dao;

import com.project.p1.bean.bo.OrderByPageBO;
import com.project.p1.bean.bo.OrderEditBO;
import com.project.p1.bean.vo.*;

import java.util.List;

public interface OrderDao {
    int getTotalCount(OrderByPageBO orderByPageBO);

    List<OrderInOrdersByPageVO> ordersByPage(OrderByPageBO orderByPageBO);

    OrderBasicInfoVO getBasicInfo(int id);

    List<SpecInOrderEditVO> getSpecList(int specId);

    List<StateInOrderEditVO> getStateList();

    void changeOrder(OrderEditBO orderEditBO);

    void deleteOrder(int id);
}

