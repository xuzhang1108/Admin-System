package com.project.p1.bean;

public class Goods {
    private Integer id;
    private String name;
    private Integer typeId;
    private String image;
    private Integer stockNum;
    private Double price;
    private String description;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goods(Integer id, String name, Integer typeId, String image, Integer stockNum, Double price, String description) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.image = image;
        this.stockNum = stockNum;
        this.price = price;
        this.description = description;
    }

    public Goods() {

    }
}
