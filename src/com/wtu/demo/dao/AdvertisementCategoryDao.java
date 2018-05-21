package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.AdvertisementCategory;

public interface AdvertisementCategoryDao {
	public List<AdvertisementCategory> getAllAdvertisementCategory();
	public boolean createAdvertisementCategory(String categoryName);
	public boolean editAdvertisementCategoryById(Long id, String categoryName);
	public AdvertisementCategory getAdvertisementCategoryById(Long id);
	public List<AdvertisementCategory> getAllAdvertisementCategoryByPagination(Long index, Long pageSize);
	public boolean deleteAdvertisementCategoryById(Long id);
	public long getAdvertisementCategoryCount();
}
