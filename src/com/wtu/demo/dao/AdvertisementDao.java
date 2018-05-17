package com.wtu.demo.dao;

public interface AdvertisementDao {
	public boolean createAdvertisement(Long categoryId, Double weight, String description, String detail);
	public String getAdvertisement(Long pkId);
}
