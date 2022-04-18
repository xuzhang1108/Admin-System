package com.project.p1.bean.bo;

import java.util.List;

public class AddGoodsBO {
    /**
     * img: image/8/5/0......
     * name: iphone13
     * typeId: 3
     * desc: 新出的iPhone13
     */
    private String img;
    private String name;
    private Integer typeId;
    private String desc;
    private List<AddGoodsSpecBO> specList;

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

    public List<AddGoodsSpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<AddGoodsSpecBO> specList) {
        this.specList = specList;
    }
}
