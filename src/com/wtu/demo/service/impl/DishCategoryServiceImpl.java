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
}
