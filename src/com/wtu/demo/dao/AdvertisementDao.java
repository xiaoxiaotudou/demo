package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Advertisement;

public interface AdvertisementDao {
	public boolean createAdvertisement(Long categoryId, Double weight, String description, String detail);
	public boolean editAdvertisement(Long id, Long categoryId, Double weight,String description, String detail);
	public Advertisement getAdvertisementById(Long pkId);
	public List<Advertisement> getAllAdvertisement(Long index, Long pageSize);
	public List<Advertisement> getAllAdvertisementByCategoryId(Long categoryId, Long index, Long pageSize);
	public boolean deleteAdvertisementById(Long id);
	public long getAdvertisementCount();
}