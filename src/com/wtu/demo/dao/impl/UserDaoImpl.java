package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.UserDao;
import com.wtu.demo.model.User;
import com.wtu.demo.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByEmail(String email) {
        User user = new User();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from user where userName = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setPkId(resultSet.getInt("pkId"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return user;
    }
}
