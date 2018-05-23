package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.Restaurant;

public interface RestaurantDao {
	public List<Restaurant> getAllRestaurantByPagination(Long index, Long pageSize);
	public List<Restaurant> getAllRestaurant();
	public Restaurant getRestaurantById(Long id);
	public long getRestaurantCount();
	public boolean deleteRestaurantById(Long id);
	public boolean createRestaurant(String name, String address, String description, String image);
	public boolean editRestaurantById(Long id, String name, String address, String description, String image);
}
