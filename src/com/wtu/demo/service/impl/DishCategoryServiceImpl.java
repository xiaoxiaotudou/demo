package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.DishCategoryDaoImpl;
import com.wtu.demo.model.DishCategory;
import com.wtu.demo.service.DishCategoryService;

@Service
public class DishCategoryServiceImpl implements DishCategoryService {

	@Autowired
	private DishCategoryDaoImpl dishCategoryDaoImpl;

	@Override
	public List<DishCategory> getAllDishCategoryByRestaurantId(String restaurantId) {
		return dishCategoryDaoImpl.getAllDishCategoryByRestaurantId(Long.valueOf(restaurantId));
	}

	@Override
	public List<DishCategory> getAllDishCategoryByPagination(String index,
			String pageSize, String restaurantId) {
		return dishCategoryDaoImpl.getAllDishCategoryByPagination(Long.valueOf(index), Long.valueOf(pageSize), Long.valueOf(restaurantId));
	}

	@Override
	public long getDishCategoryCount() {
		return dishCategoryDaoImpl.getDishCategoryCount();
	}

	@Override
	public DishCategory getDishCategoryById(String id) {
		return dishCategoryDaoImpl.getDishCategoryById(Long.valueOf(id));
	}

	@Override
	public boolean deleteDishCategoryById(String id) {
		return dishCategoryDaoImpl.deleteDishCategoryById(Long.valueOf(id));
	}
}