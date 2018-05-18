package com.wtu.demo.model;

public class DishCategory {
	private long pkId;
	private String dishCategoryName;

	public long getPkId() {
		return pkId;
	}
	public void setPkId(long pkId) {
		this.pkId = pkId;
	}
	public String getDishCategoryName() {
		return dishCategoryName;
	}
	public void setDishCategoryName(String dishCategoryName) {
		this.dishCategoryName = dishCategoryName;
	}
}