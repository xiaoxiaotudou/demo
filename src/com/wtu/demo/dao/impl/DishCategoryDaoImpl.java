package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
            	dishCategory.setName(resultSet.getString("dishCategoryName"));
            	dishCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

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

	@Override
    public List<DishCategory> getAllDishCategoryByPagination(Long index, Long pageSize, Long restaurantId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<DishCategory> dishCategories = new ArrayList<DishCategory>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish_category where pkId in (select dishCategoryId from restaurant_dishcategory where restaurantId = ?) and deleted = 0 limit ?, ?");

            preparedStatement.setLong(1, restaurantId);
            preparedStatement.setLong(2, (index - 1) * pageSize);
            preparedStatement.setLong(3, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	DishCategory dishCategory = new DishCategory();

            	dishCategory.setPkId(resultSet.getLong("pkId"));
            	dishCategory.setName(resultSet.getString("dishCategoryName"));
            	dishCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

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

	@Override
    public long getDishCategoryCount() {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long count = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from restaurant_dishcategory where deleted = 0");
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
	public DishCategory getDishCategoryById(Long id) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DishCategory dishCategory = new DishCategory();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from dish_category where pkId = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

            	dishCategory.setPkId(resultSet.getLong("pkId"));
            	dishCategory.setName(resultSet.getString("dishCategoryName"));
            	dishCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return dishCategory;
	}

	@Override
    public boolean deleteDishCategoryById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update dish_category set deleted = 1 where pkId = ?");
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
	public boolean editDishCategory(Long categoryId, String categoryName) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update dish_category set dishCategoryName = ? where pkId = ?");
            preparedStatement.setString(1, categoryName);
            preparedStatement.setLong(2, categoryId);

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
	public boolean createDishCategory(Long restaurantId, String categoryName) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long pkId = 0;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into dish_category(dishCategoryName) values(?);select @@identity pkId");
            preparedStatement.setString(1, categoryName);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	pkId = resultSet.getLong("pkId");
            }

            result = !connection.prepareStatement("insert into restaurant_dishcategory(restaurantId, dishCategoryId) values(" + restaurantId +"," + pkId + ")").execute();
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
