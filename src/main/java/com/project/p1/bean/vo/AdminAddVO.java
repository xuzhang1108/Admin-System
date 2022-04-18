package com.project.p1.bean.vo;

public class AdminAddVO {
    private String email;

    private String nickname;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public AdminAddVO() {
    }

    public AdminAddVO(String email, String nickname, String pwd) {
        this.email = email;
        this.nickname = nickname;
        this.pwd = pwd;
    }
}
