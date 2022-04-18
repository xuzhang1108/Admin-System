package com.project.p1.bean.vo;

public class OrderInOrdersByPageVO {
    private Integer id;
    private Integer userId;
    private Integer goodsDetailId;
    private String goods;
    private String spec;
    private Integer goodsNum;
    private Double amount;
    private Integer stateId;
    private String state;
    private UserOfOrderInOrdersByPageVO user = new UserOfOrderInOrdersByPageVO();

    // 它们最后是null不会显示在json字符串中
    private String nickname;
    private String phone;
    private String address;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
        if (stateId == 0) {
            setState("未付款");
        } else if (stateId == 1) {
            setState("未发货");
        } else if (stateId == 2) {
            setState("已发货");
        } else {
            setState("已到货");
        }
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public UserOfOrderInOrdersByPageVO getUser() {
        return user;
    }

    public void setUser(UserOfOrderInOrdersByPageVO user) {
        this.user = user;
    }

    public void setNickname(String nickname) {
        user.setNickname(nickname);
    }

    public void setPhone(String phone) {
        user.setPhone(phone);
    }

    public void setAddress(String address) {
        user.setAddress(address);
    }

    public void setName(String name) {
        user.setName(name);
    }
}
