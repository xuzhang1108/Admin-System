package com.project.p1.bean.bo;

public class AddGoodsSpecBO {
    /**
     * unitPrice: 5699
     * specName: 128G
     * stockNum: 1000
     */
    private Double unitPrice;
    private String specName;
    private Integer stockNum;

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public AddGoodsSpecBO() {
    }

    public AddGoodsSpecBO(Double unitPrice, String specName, Integer stockNum) {
        this.unitPrice = unitPrice;
        this.specName = specName;
        this.stockNum = stockNum;
    }
}
