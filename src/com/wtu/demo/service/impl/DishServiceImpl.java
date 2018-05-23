package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.DishDaoImpl;
import com.wtu.demo.model.Dish;
import com.wtu.demo.service.DishService;

@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private DishDaoImpl dishDaoImpl;

	@Override
	public List<Dish> getAllDishByCategoryId(String categoryId) {
		return dishDaoImpl.getAllDishByCategoryId(Long.valueOf(categoryId));
	}

	@Override
	public List<Dish> getAllDishByPagination(String dishCategoryId,
			String index, String pageSize) {
		return dishDaoImpl.getAllDishByPagination(Long.valueOf(dishCategoryId), Long.valueOf(index), Long.valueOf(pageSize));
	}

	@Override
	public long getAllDishCountByCategoryId(String dishCategoryId) {
		return dishDaoImpl.getAllDishCountByCategoryId(Long.valueOf(dishCategoryId));
	}

	@Override
	public boolean deleteDishById(String id) {
		return dishDaoImpl.deleteDishById(Long.valueOf(id));
	}

	@Override
	public Dish getDishById(String id) {
		return dishDaoImpl.getDishById(Long.valueOf(id));
	}

	@Override
	public boolean editDish(String id, String dishName, String dishPrice,
			String dishImage) {
		return dishDaoImpl.editDish(Long.valueOf(id), dishName, Double.valueOf(dishPrice), dishImage);
	}

	@Override
	public boolean createDish(String dishCategoryId, String dishName,
			String dishPrice, String dishImage) {
		return dishDaoImpl.createDish(Long.valueOf(dishCategoryId), dishName, Double.valueOf(dishPrice), dishImage);
	}
}