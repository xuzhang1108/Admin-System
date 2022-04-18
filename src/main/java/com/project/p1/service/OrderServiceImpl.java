package com.project.p1.service;

import com.project.p1.bean.bo.OrderByPageBO;
import com.project.p1.bean.bo.OrderEditBO;
import com.project.p1.bean.vo.*;
import com.project.p1.dao.OrderDao;
import com.project.p1.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    /**
     * 返回total符合条件的总数目和orders：当前页面所需的数据
     * @param orderByPageBO
     * @return
     */
    @Override
    public OrdersByPageVO ordersByPage(OrderByPageBO orderByPageBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        int cnt = orderDao.getTotalCount(orderByPageBO);
        List<OrderInOrdersByPageVO> orders = orderDao.ordersByPage(orderByPageBO);
        sqlSession.commit();
        sqlSession.close();
        OrdersByPageVO ordersByPageVO = new OrdersByPageVO(cnt, orders);
        return ordersByPageVO;
    }

    @Override
    public OrderEditVO order(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        OrderBasicInfoVO orderBasicInfoVO = orderDao.getBasicInfo(id);
        List<SpecInOrderEditVO> specs = orderDao.getSpecList(orderBasicInfoVO.getSpecId());
        List<StateInOrderEditVO> states = orderDao.getStateList();
        OrderEditVO orderEditVO = new OrderEditVO(orderBasicInfoVO.getGoods(), orderBasicInfoVO.getAmount(), orderBasicInfoVO.getSpecId(), orderBasicInfoVO.getStateId(), specs, orderBasicInfoVO.getNum(), states);
        sqlSession.commit();
        sqlSession.close();
        return orderEditVO;
    }

    @Override
    public void changeOrder(OrderEditBO orderEditBO) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.changeOrder(orderEditBO);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteOrder(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.deleteOrder(id);
        sqlSession.commit();
        sqlSession.close();
    }

}
