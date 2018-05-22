package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.Restaurant;

public interface RestaurantService {
	public List<Restaurant> getAllRestaurantByPagination(String index, String pageSize);
	public List<Restaurant> getAllRestaurant();
	public Restaurant getRestaurantById(String id);
	public long getRestaurantCount();
	public boolean deleteRestaurantById(String id);
	public boolean createRestaurant(String restaurantame, String restaurantAddress, String restaurantImage);
	public boolean editRestaurantById(String id, String restaurantame, String restaurantAddress, String restaurantImage);
}
