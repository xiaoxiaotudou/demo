package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.DishCategory;

public interface DishCategoryDao {
	public List<DishCategory> getAllDishCategoryByRestaurantId(Long restaurantId);
	public List<DishCategory> getAllDishCategoryByPagination(Long index, Long pageSize, Long restaurantId);
	public long getDishCategoryCount();
	public DishCategory getDishCategoryById(Long id);
	public boolean deleteDishCategoryById(Long id);
	public boolean editDishCategory(Long categoryId, String categoryName);
	public boolean createDishCategory(Long restaurantId, String categoryName);
}
