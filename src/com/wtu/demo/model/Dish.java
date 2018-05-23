package com.wtu.demo.model;

public class Dish {
	private long pkId;
	private long dishCategoryId;
	private String dishName;
	private double dishPrice;
	private String image;
	private String createdTime;

	public long getPkId() {
		return pkId;
	}
	public void setPkId(long pkId) {
		this.pkId = pkId;
	}
	public long getDishCategoryId() {
		return dishCategoryId;
	}
	public void setDishCategoryId(long dishCategoryId) {
		this.dishCategoryId = dishCategoryId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public double getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(double dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}