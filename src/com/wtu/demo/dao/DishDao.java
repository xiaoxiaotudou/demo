package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Dish;

public interface DishDao {
	public List<Dish> getAllDishByCategoryId(Long categoryId);
}