package com.project.p1.bean.vo;

import com.project.p1.bean.bo.AddGoodsSpecBO;

import java.util.List;

public class GoodsInfoVO {
    private GoodsVO goods;
    private List<AddGoodsSpecBO> specs;

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    public List<AddGoodsSpecBO> getSpecList() {
        return specs;
    }

    public void setSpecList(List<AddGoodsSpecBO> specList) {
        this.specs = specList;
    }

    public GoodsInfoVO() {
    }

    public GoodsInfoVO(GoodsVO goods, List<AddGoodsSpecBO> specList) {
        this.goods = goods;
        this.specs = specList;
    }
}
