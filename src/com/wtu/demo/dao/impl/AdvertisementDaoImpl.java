package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.AdvertisementDao;
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
            preparedStatement = connection.prepareStatement("insert into advertisement(categoryId, weight, description, detail, deleted) values(?, ?, ?, ?, 0)");
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
        
        return result;
	}

	@Override
	public String getAdvertisement(Long pkId) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String detail = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select detail from advertisement where pkId = ?");
            preparedStatement.setLong(1, pkId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	detail = resultSet.getString("detail");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }
        
        return detail;
	}
}
