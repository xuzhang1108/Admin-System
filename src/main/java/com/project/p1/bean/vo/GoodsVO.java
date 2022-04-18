package com.project.p1.bean.vo;

public class GoodsVO {
    private String img;
    private String name;
    private Integer typeId;
    private String desc;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public GoodsVO() {
    }

    public GoodsVO(String img, String name, Integer typeId, String desc) {
        this.img = img;
        this.name = name;
        this.typeId = typeId;
        this.desc = desc;
    }
}
