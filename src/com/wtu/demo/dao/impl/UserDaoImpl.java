package com.wtu.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtu.demo.dao.UserDao;
import com.wtu.demo.model.User;
import com.wtu.demo.util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByEmail(String account) {
        User user = new User();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from user where account = ?");
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setPkId(resultSet.getInt("pkId"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getString("gender"));
                user.setAdmin(Boolean.valueOf(resultSet.getString("isAdmin")));
                user.setTel(resultSet.getString("tel"));
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

    @Override
	public List<User> getAllUserByPagination(Long index, Long pageSize) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select pkId, account, userName, tel, gender, createdTime from user where deleted = 0 limit ?,?");
            preparedStatement.setLong(1, (index - 1) * pageSize);
            preparedStatement.setLong(2, pageSize);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
				User user = new User();

				user.setPkId(resultSet.getInt("pkId"));
                user.setAccount(resultSet.getString("account"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getString("gender"));
                user.setTel(resultSet.getString("tel"));
				user.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));

				users.add(user);
			}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            DBUtil.close(resultSet, preparedStatement, connection);
        } finally {
            DBUtil.close(resultSet, preparedStatement, connection);
        }

        return users;
	}

	@Override
	public User getUserById(Long id) {
		User user = new User();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select * from user where pkId = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setPkId(resultSet.getInt("pkId"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getString("gender"));
                user.setTel(resultSet.getString("tel"));
				user.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("createdTime").getTime()));
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

	@Override
	public boolean deleteUserById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update user set deleted = 1 where pkId = ?");
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
	public boolean createUser(String account, String password, String userName, String gender, String tel) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("insert into user(account, password, userName, tel, gender) values(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, tel);
            preparedStatement.setString(5, gender);

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
	public boolean editUser(Long pkId, String password, String userName, String gender, String tel) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("update user set password = ?, userName = ?, gender = ?,  tel = ? where pkId = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, tel);
            preparedStatement.setLong(5, pkId);

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
	public long geyAllUserCount() {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long result = 0;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement("select count(1) from user where deleted = 0");

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
}
