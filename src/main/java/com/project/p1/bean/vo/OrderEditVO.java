package com.project.p1.bean.vo;


import java.util.List;

public class OrderEditVO {
    private String goods;
    private Double amount;
    private Integer specId;
    private Integer stateId;
    private List<SpecInOrderEditVO> spec;
    private Integer num;
    private List<StateInOrderEditVO> states;
    private CurrentSpec curSpec = new CurrentSpec();
    private CurrentState curState = new CurrentState();

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSpecId() {
        return specId;
    }

    public List<SpecInOrderEditVO> getSpec() {
        return spec;
    }

    public void setSpec(List<SpecInOrderEditVO> spec) {
        this.spec = spec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public List<StateInOrderEditVO> getStates() {
        return states;
    }

    public void setStates(List<StateInOrderEditVO> states) {
        this.states = states;
    }

    public OrderEditVO() {
    }

    public OrderEditVO(String goods, Double amount, Integer specId, Integer stateId, List<SpecInOrderEditVO> spec, Integer num, List<StateInOrderEditVO> states) {
        this.goods = goods;
        this.amount = amount;
        this.curSpec.id = specId;
        this.curState.id = stateId;
        this.spec = spec;
        this.num = num;
        this.states = states;
    }
}
