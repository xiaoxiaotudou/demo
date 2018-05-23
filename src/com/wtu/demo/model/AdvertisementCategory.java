package com.wtu.demo.model;


public class AdvertisementCategory {
    private long pkId;
    private String categoryName;
    private String createdTime;

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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}