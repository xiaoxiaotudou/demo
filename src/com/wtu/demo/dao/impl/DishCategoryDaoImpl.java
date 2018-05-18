package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.DishCategoryDao;
import com.wtu.demo.model.DishCategory;
import com.wtu.demo.util.DBUtil;

@Repository
public class DishCategoryDaoImpl implements DishCategoryDao {

	@Override
	public List<DishCategory> getAllDishCategoryByRestaurantId(Long restaurantId) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<DishCategory> dishCategories = new ArrayList<DishCategory>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish_category where pkId in (select dishCategoryId from restaurant_dishcategory where restaurantId = ?) and deleted = 0");
            preparedStatement.setLong(1, restaurantId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	DishCategory dishCategory = new DishCategory();

            	dishCategory.setPkId(resultSet.getLong("pkId"));
            	dishCategory.setDishCategoryName(resultSet.getString("dishCategoryName"));

            	dishCategories.add(dishCategory);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return dishCategories;
	}
}
