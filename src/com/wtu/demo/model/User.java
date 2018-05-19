package com.wtu.demo.model;

public class User {
    private long pkId = -1;
    private String userName = null;
    private String password = null;

    public long getPkId() {
        return pkId;
    }
    public void setPkId(long pkId) {
        this.pkId = pkId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}