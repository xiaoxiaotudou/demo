package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.Dish;

public interface DishService {
	public List<Dish> getAllDishByCategoryId(String categoryId);
}