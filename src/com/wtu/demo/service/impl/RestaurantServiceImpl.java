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
	public List<Restaurant> getAllRestaurantByPagination(String index, String pageSize) {
		return restaurantDaoImpl.getAllRestaurantByPagination(Long.valueOf(index), Long.valueOf(pageSize));
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantDaoImpl.getAllRestaurant();
	}

	@Override
	public Restaurant getRestaurantById(String id) {
		return restaurantDaoImpl.getRestaurantById(Long.valueOf(id));
	}

	@Override
	public long getRestaurantCount() {
		return restaurantDaoImpl.getRestaurantCount();
	}

	@Override
	public boolean deleteRestaurantById(String id) {
		return restaurantDaoImpl.deleteRestaurantById(Long.valueOf(id));
	}

	@Override
	public boolean createRestaurant(String restaurantame,
			String restaurantAddress, String restaurantImage) {
		return restaurantDaoImpl.createRestaurant(restaurantame, restaurantAddress, restaurantImage);
	}

	@Override
	public boolean editRestaurantById(String id, String restaurantame,
			String restaurantAddress, String restaurantImage) {
		return restaurantDaoImpl.editRestaurantById(Long.valueOf(id), restaurantame, restaurantAddress, restaurantImage);
	}
}