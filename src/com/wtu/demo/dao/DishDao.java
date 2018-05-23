package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Dish;

public interface DishDao {
	public List<Dish> getAllDishByCategoryId(Long categoryId);
	public List<Dish> getAllDishByPagination(Long dishCategoryId, Long index, Long pageSize);
	public long getAllDishCountByCategoryId(Long dishCategoryId);
	public boolean deleteDishById(Long id);
	public Dish getDishById(Long id);
	public boolean editDish(Long id, String dishName, Double dishPrice, String dishImage);
	public boolean createDish(Long dishCategoryId, String dishName, Double dishPrice, String dishImage);
}