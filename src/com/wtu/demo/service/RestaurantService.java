package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.Restaurant;

public interface RestaurantService {
	public List<Restaurant> getAllRestaurant(String index, String pageSize);
}
