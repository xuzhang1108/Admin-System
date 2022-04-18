package com.project.p1.dao;

import com.project.p1.bean.Goods;
import com.project.p1.bean.Specification;
import com.project.p1.bean.Type;
import com.project.p1.bean.bo.AddGoodsSpecBO;
import com.project.p1.bean.vo.GoodsByTypeVO;

import java.util.List;

public interface GoodsDao {
    List<Type> getType();

    List<GoodsByTypeVO> getGoodsByType(int typeId);

    void addGoods(Goods goods);

    void addSpecs(List<Specification> spec);

    void addType(Type type);

    void deleteType(int typeId);

    Goods getGoodsInfo(int id);

    List<Specification> getSpecList(int id);

    void deleteGoods(int id);

    void deleteSpec(int id);
}
