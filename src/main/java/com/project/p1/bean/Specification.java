package com.project.p1.bean;

public class Specification {
    private Integer id;
    private String name;
    private Integer stockNum;
    private Double price;
    private Integer goodsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Specification(Integer id, String name, Integer stockNum, Double price, Integer goodsId) {
        this.id = id;
        this.name = name;
        this.stockNum = stockNum;
        this.price = price;
        this.goodsId = goodsId;
    }

    public Specification() {
    }
}
