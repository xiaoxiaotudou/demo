package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.AdvertisementCategoryDao;
import com.wtu.demo.model.AdvertisementCategory;
import com.wtu.demo.util.DBUtil;

@Repository
public class AdvertisementCategoryDaoImpl implements AdvertisementCategoryDao {

	@Override
    public List<AdvertisementCategory> getAllAdvertisementCategory() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<AdvertisementCategory> advertisementCategories = new ArrayList<AdvertisementCategory>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement_category where deleted = 0");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AdvertisementCategory advertisementCategory = new AdvertisementCategory();

                advertisementCategory.setPkId(resultSet.getLong("pkId"));
                advertisementCategory.setCategoryName(resultSet.getString("categoryName"));
                advertisementCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

                advertisementCategories.add(advertisementCategory);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisementCategories;
    }

	@Override
    public boolean createAdvertisementCategory(String categoryName) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into advertisement_category(categoryName) values(?)");
            preparedStatement.setString(1, categoryName);

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
    public boolean editAdvertisementCategoryById(Long id, String categoryName) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update advertisement_category set categoryName = ? where pkId = ?");
            preparedStatement.setString(1, categoryName);
            preparedStatement.setLong(2, id);

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
    public AdvertisementCategory getAdvertisementCategoryById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AdvertisementCategory advertisementCategory = new AdvertisementCategory();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement_category where pkId = ?");

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                advertisementCategory.setPkId(resultSet.getLong("pkId"));
                advertisementCategory.setCategoryName(resultSet.getString("categoryName"));
                advertisementCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisementCategory;
    }

    @Override
    public List<AdvertisementCategory> getAllAdvertisementCategoryByPagination(Long index, Long pageSize) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<AdvertisementCategory> advertisementCategories = new ArrayList<AdvertisementCategory>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement_category where deleted = 0 limit ?, ?");

            preparedStatement.setLong(1, (index - 1) * pageSize);
            preparedStatement.setLong(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AdvertisementCategory advertisementCategory = new AdvertisementCategory();

                advertisementCategory.setPkId(resultSet.getLong("pkId"));
                advertisementCategory.setCategoryName(resultSet.getString("categoryName"));
                advertisementCategory.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

                advertisementCategories.add(advertisementCategory);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisementCategories;
    }

    @Override
    public boolean deleteAdvertisementCategoryById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update advertisement_category set deleted = 1 where pkId = ?");
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
    public long getAdvertisementCategoryCount() {
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long count = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from advertisement_category where deleted = 0");
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
}
