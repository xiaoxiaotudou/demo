package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Restaurant;

public interface RestaurantDao {
	public List<Restaurant> getAllRestaurantByPagination(Long index, Long pageSize);
	public List<Restaurant> getAllRestaurant();
	public Restaurant getRestaurantById(Long id);
	public long getRestaurantCount();
	public boolean deleteRestaurantById(Long id);
	public boolean createRestaurant(String restaurantame, String restaurantAddress, String restaurantImage);
	public boolean editRestaurantById(Long id, String restaurantame, String restaurantAddress, String restaurantImage);
}
