package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.Dish;

public interface DishService {
	public List<Dish> getAllDishByCategoryId(String categoryId);
	public List<Dish> getAllDishByPagination(String dishCategoryId, String index, String pageSize);
	public long getAllDishCountByCategoryId(String dishCategoryId);
	public boolean deleteDishById(String id);
	public Dish getDishById(String id);
	public boolean editDish(String id, String dishName, String dishPrice, String dishImage);
	public boolean createDish(String dishCategoryId, String dishName, String dishPrice, String dishImage);
}