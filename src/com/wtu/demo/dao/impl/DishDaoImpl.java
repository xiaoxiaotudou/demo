package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            	dish.setDishCategoryId(resultSet.getLong("categoryId"));
            	dish.setDishName(resultSet.getString("dishName"));
            	dish.setDishPrice(resultSet.getDouble("dishPrice"));
            	dish.setImage(resultSet.getString("image"));

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
}
