package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.RestaurantDaoImpl;
import com.wtu.demo.model.Restaurant;
import com.wtu.demo.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantDaoImpl restaurantDaoImpl;

	@Override
	public List<Restaurant> getAllRestaurant(String index, String pageSize) {
		return restaurantDaoImpl.getAllRestaurant(Long.valueOf(index), Long.valueOf(pageSize));
	}
}