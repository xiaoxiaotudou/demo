package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.DishCategory;

public interface DishCategoryService {
	public List<DishCategory> getAllDishCategoryByRestaurantId(String restaurantId);
	public List<DishCategory> getAllDishCategoryByPagination(String index, String pageSize, String restaurantId);
	public long getDishCategoryCount();
	public DishCategory getDishCategoryById(String id);
	public boolean deleteDishCategoryById(String id);
	public boolean editDishCategory(String categoryId, String categoryName);
	public boolean createDishCategory(String restaurantId, String categoryName);
}