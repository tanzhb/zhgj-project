package com.congmai.zhgj.web.model;

public class UserCompanyKey {
    private String user_id;

    private String com_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    public String getCom_id() {
        return com_id;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id == null ? null : com_id.trim();
    }
}