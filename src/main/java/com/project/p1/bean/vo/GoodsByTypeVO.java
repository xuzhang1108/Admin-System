package com.project.p1.bean.vo;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoodsByTypeVO {
    private  static String domain;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            domain = properties.getProperty("domain");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer id;
    private String name;
    private Integer typeId;
    private String img;
    private Integer stockNum;
    private Double price;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = domain + img;
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
}
