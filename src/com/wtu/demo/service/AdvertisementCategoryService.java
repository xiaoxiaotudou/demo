package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.AdvertisementCategory;

public interface AdvertisementCategoryService {
	public List<AdvertisementCategory> getAllAdvertisementCategory();
	public boolean editAdvertisementCategoryById(String id, String categoryName);
	public boolean createAdvertisementCategory(String categoryName);
	public AdvertisementCategory getAdvertisementCategoryById(String id);
	public List<AdvertisementCategory> getAllAdvertisementCategoryByPagination(String index, String pageSize);
    public boolean deleteAdvertisementCategoryById(String id);
    public long getAdvertisementCategoryCount();
}
