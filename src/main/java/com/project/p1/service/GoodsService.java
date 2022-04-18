package com.project.p1.service;

import com.project.p1.bean.Type;
import com.project.p1.bean.bo.AddGoodsBO;
import com.project.p1.bean.bo.AddTypeBO;
import com.project.p1.bean.vo.GoodsByTypeVO;
import com.project.p1.bean.vo.GoodsInfoVO;

import java.util.List;

public interface GoodsService {
    List<Type> getType();

    List<GoodsByTypeVO> getGoodsByType(int parseInt);

    void addGoods(AddGoodsBO addGoodsBO);

    void addType(AddTypeBO addTypeBO);

    void deleteType(int parseInt);

    GoodsInfoVO getGoodsInfo(int parseInt);

    void deleteGoods(int parseInt);
}
