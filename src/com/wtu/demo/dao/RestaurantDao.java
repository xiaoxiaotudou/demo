package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Restaurant;

public interface RestaurantDao {
	public List<Restaurant> getAllRestaurant(Long index, Long pageSize);
}
