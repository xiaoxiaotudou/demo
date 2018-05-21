package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.AdvertisementDao;
import com.wtu.demo.model.Advertisement;
import com.wtu.demo.util.DBUtil;

@Repository
public class AdvertisementDaoImpl implements AdvertisementDao {

	@Override
	public boolean createAdvertisement(Long categoryId, Double weight,
			String description, String detail) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into advertisement(categoryId, weight, description, detail) values(?, ?, ?, ?)");
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setDouble(2, weight);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, detail);

            result = preparedStatement.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return !result;
	}

	@Override
	public Advertisement getAdvertisementById(Long pkId) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Advertisement advertisement = new Advertisement();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement where pkId = ?");
            preparedStatement.setLong(1, pkId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	advertisement.setPkId(resultSet.getLong("pkId"));
            	advertisement.setCategoryId(resultSet.getLong("categoryId"));
            	advertisement.setDescription(resultSet.getString("description"));
            	advertisement.setDetail(resultSet.getString("detail"));
            	advertisement.setWeight(resultSet.getDouble("weight"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisement;
	}

	@Override
	public List<Advertisement> getAllAdvertisement(Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Advertisement> advertisements = new ArrayList<Advertisement>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement limit ?,?");
            preparedStatement.setLong(1, index);
            preparedStatement.setLong(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
				Advertisement advertisement = new Advertisement();

				advertisement.setPkId(resultSet.getLong("pkId"));
				advertisement.setCategoryId(resultSet.getLong("categoryId"));
				advertisement.setDescription(resultSet.getString("description"));
				advertisement.setDetail(resultSet.getString("detail"));
				advertisement.setWeight(resultSet.getDouble("weight"));

				advertisements.add(advertisement);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisements;
	}

	@Override
	public List<Advertisement> getAllAdvertisementByCategoryId(Long categoryId,
			Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Advertisement> advertisements = new ArrayList<Advertisement>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from advertisement where categoryId = ? limit ?,?");
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setLong(2, index);
            preparedStatement.setLong(3, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
				Advertisement advertisement = new Advertisement();

				advertisement.setPkId(resultSet.getLong("pkId"));
				advertisement.setCategoryId(resultSet.getLong("categoryId"));
				advertisement.setDescription(resultSet.getString("description"));
				advertisement.setDetail(resultSet.getString("detail"));
				advertisement.setWeight(resultSet.getDouble("weight"));

				advertisements.add(advertisement);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return advertisements;
	}

    @Override
    public boolean deleteAdvertisementById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update advertisement set deleted = 1 where pkId = ?");
            preparedStatement.setLong(1, id);

            result = preparedStatement.execute();
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
    public long getAdvertisementCount() {
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long count = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from advertisement where deleted = 0");
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