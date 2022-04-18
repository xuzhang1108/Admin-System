package com.project.p1.bean.bo;

public class AdminAddBO {
    private String nickname;
    private String email;
    private String pwd;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public AdminAddBO() {
    }

    public AdminAddBO(String nickname, String email, String pwd) {
        this.nickname = nickname;
        this.email = email;
        this.pwd = pwd;
    }
}
