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
}