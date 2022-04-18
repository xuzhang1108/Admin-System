package com.project.p1.bean.vo;

public class StateInOrderEditVO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer stateId) {
        this.id = stateId - 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
