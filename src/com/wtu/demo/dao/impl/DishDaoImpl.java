package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.DishDao;
import com.wtu.demo.model.Dish;
import com.wtu.demo.util.DBUtil;

@Repository
public class DishDaoImpl implements DishDao {

	@Override
	public List<Dish> getAllDishByCategoryId(Long categoryId) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishs = new ArrayList<Dish>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish where dishCategoryId = ? and deleted = 0");
            preparedStatement.setLong(1, categoryId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Dish dish = new Dish();

            	dish.setPkId(resultSet.getLong("pkId"));
            	dish.setDishCategoryId(resultSet.getLong("dishCategoryId"));
            	dish.setDishName(resultSet.getString("dishName"));
            	dish.setDishPrice(resultSet.getDouble("dishPrice"));
            	dish.setImage(resultSet.getString("image"));
            	dish.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

            	dishs.add(dish);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return dishs;
	}

	@Override
	public List<Dish> getAllDishByPagination(Long dishCategoryId, Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishs = new ArrayList<Dish>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish where dishCategoryId = ? and deleted = 0 limit ?, ?");

            preparedStatement.setLong(1, dishCategoryId);
            preparedStatement.setLong(2, (index - 1) * pageSize);
            preparedStatement.setLong(3, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	Dish dish = new Dish();

            	dish.setPkId(resultSet.getLong("pkId"));
            	dish.setDishCategoryId(resultSet.getLong("dishCategoryId"));
            	dish.setDishName(resultSet.getString("dishName"));
            	dish.setDishPrice(resultSet.getDouble("dishPrice"));
            	dish.setImage(resultSet.getString("image"));
            	dish.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

            	dishs.add(dish);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return dishs;
	}

	@Override
	public long getAllDishCountByCategoryId(Long dishCategoryId) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long result = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from dish where dishCategoryId = ? and deleted = 0");

            preparedStatement.setLong(1, dishCategoryId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	result = resultSet.getLong(1);
			}
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
    public boolean deleteDishById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update dish set deleted = 1 where pkId = ?");
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
	public Dish getDishById(Long id) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dish dish = new Dish();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish where pkId = ?");

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

            	dish.setPkId(resultSet.getLong("pkId"));
            	dish.setDishCategoryId(resultSet.getLong("dishCategoryId"));
            	dish.setDishName(resultSet.getString("dishName"));
            	dish.setDishPrice(resultSet.getDouble("dishPrice"));
            	dish.setImage(resultSet.getString("image"));
            	dish.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return dish;
	}

	@Override
	public boolean editDish(Long id, String dishName, Double dishPrice,
			String dishImage) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update dish set dishName = ?, dishPrice = ?, dishImage = ? where pkId = ?");

            preparedStatement.setString(1, dishName);
            preparedStatement.setDouble(2, dishPrice);
            preparedStatement.setString(3, dishImage);
            preparedStatement.setLong(4, id);

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
	public boolean createDish(Long dishCategoryId, String dishName, Double dishPrice,
			String dishImage) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into dish(dishCategoryId, dishName, dishPrice, dishImage values(?, ?, ?, ?))");
            preparedStatement.setLong(1, dishCategoryId);
            preparedStatement.setString(2, dishName);
            preparedStatement.setDouble(3, dishPrice);
            preparedStatement.setString(4, dishImage);

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
