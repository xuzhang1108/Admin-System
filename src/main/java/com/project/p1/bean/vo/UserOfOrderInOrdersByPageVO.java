package com.project.p1.bean.vo;

public class UserOfOrderInOrdersByPageVO {
    private String nickname;
    private String phone;
    private String address;
    private String name;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserOfOrderInOrdersByPageVO() {
    }

    public UserOfOrderInOrdersByPageVO(String nickname, String phone, String address, String name) {
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
        this.name = name;
    }
}
