package com.wtu.demo.model;

public class AdvertisementCategory {
    private long pkId = -1;
    private String categoryName = null;

    public long getPkId() {
        return pkId;
    }
    public void setPkId(long pkId) {
        this.pkId = pkId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}