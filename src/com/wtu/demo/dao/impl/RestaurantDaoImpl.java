package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.RestaurantDao;
import com.wtu.demo.model.Restaurant;
import com.wtu.demo.util.DBUtil;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

	@Override
	public List<Restaurant> getAllRestaurantByPagination(Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from restaurant where deleted = 0 limit ?,?");
            preparedStatement.setLong(1, (index - 1) * pageSize);
            preparedStatement.setLong(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Restaurant restaurant = new Restaurant();

            	restaurant.setPkId(resultSet.getLong("pkId"));
            	restaurant.setName(resultSet.getString("name"));
            	restaurant.setAddress(resultSet.getString("address"));
            	restaurant.setDescription(resultSet.getString("description"));
            	restaurant.setImage(resultSet.getString("image"));
            	restaurant.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

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

	@Override
	public List<Restaurant> getAllRestaurant() {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from restaurant where deleted = 0");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Restaurant restaurant = new Restaurant();

            	restaurant.setPkId(resultSet.getLong("pkId"));
            	restaurant.setName(resultSet.getString("name"));
            	restaurant.setAddress(resultSet.getString("address"));
            	restaurant.setDescription(resultSet.getString("description"));
            	restaurant.setImage(resultSet.getString("image"));
            	restaurant.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

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

	@Override
	public long getRestaurantCount() {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long count = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from restaurant where deleted = 0");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	count = resultSet.getLong(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return count;
	}

	@Override
	public boolean deleteRestaurantById(Long id) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update restaurant set deleted = 1 where pkId = ?");
            preparedStatement.setLong(1, id);

            result = !preparedStatement.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return result;
	}

	@Override
	public Restaurant getRestaurantById(Long id) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Restaurant restaurant = new Restaurant();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from restaurant where pkId = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

            	restaurant.setPkId(resultSet.getLong("pkId"));
            	restaurant.setName(resultSet.getString("name"));
            	restaurant.setAddress(resultSet.getString("address"));
            	restaurant.setDescription(resultSet.getString("description"));
            	restaurant.setImage(resultSet.getString("image"));
            	restaurant.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));
			}
        } catch (Exception e) {
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return restaurant;
	}

	@Override
	public boolean createRestaurant(String name, String address, String description, String image) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into restaurant(name, address, description, image) values(?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, image);

            result = !preparedStatement.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return result;
	}

	@Override
	public boolean editRestaurantById(Long id, String name, String address, String description, String image) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update restaurant set name = ?, address = ?, description = ?, image = ? where pkId = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, image);
            preparedStatement.setLong(5, id);

            result = !preparedStatement.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return result;
	}
}
