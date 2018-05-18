package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.DishCategory;

public interface DishCategoryService {
	public List<DishCategory> getAllDishCategoryByRestaurantId(String restaurantId);
}