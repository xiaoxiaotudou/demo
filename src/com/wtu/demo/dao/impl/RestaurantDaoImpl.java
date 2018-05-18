package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.RestaurantDao;
import com.wtu.demo.model.Restaurant;
import com.wtu.demo.util.DBUtil;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Override
	public List<Restaurant> getAllRestaurant(Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from restaurant limit ?,?");
            preparedStatement.setLong(1, index);
            preparedStatement.setLong(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Restaurant restaurant = new Restaurant();

            	restaurant.setPkId(resultSet.getLong("pkId"));
            	restaurant.setRestaurantName(resultSet.getString("restaurantName"));
            	restaurant.setDescription(resultSet.getString("description"));
            	restaurant.setImage(resultSet.getString("image"));

            	restaurants.add(restaurant);
			}
        } catch (Exception e) {
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return restaurants;
	}
}
