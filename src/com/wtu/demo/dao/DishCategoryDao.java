package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.DishCategory;

public interface DishCategoryDao {
	public List<DishCategory> getAllDishCategoryByRestaurantId(Long restaurantId);
}
