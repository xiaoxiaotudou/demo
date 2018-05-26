package com.wtu.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtu.demo.dao.impl.UserDaoImpl;
import com.wtu.demo.model.User;
import com.wtu.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public boolean checkSignIn(String email, String password) {
        User user = userDaoImpl.getUserByEmail(email);
        return user.getUserName() != null && user.getPassword() != null && user.getUserName().equals(email) && user.getPassword().equals(password);
    }

	@Override
	public List<User> getAllUserByPagination(String index, String pageSize) {
		return userDaoImpl.getAllUserByPagination(Long.valueOf(index), Long.valueOf(pageSize));
	}

	@Override
	public User getUserById(String id) {
		return userDaoImpl.getUserById(Long.valueOf(id));
	}

	@Override
	public boolean deleteUserById(String id) {
		return userDaoImpl.deleteUserById(Long.valueOf(id));
	}

	@Override
	public boolean createUser(String userName, String tel, String firstName,
			String lastName, String gender, String password) {
		return userDaoImpl.createUser(userName, tel, firstName, lastName, gender, password);
	}

	@Override
	public boolean editUser(String pkId, String tel, String firstName,
			String lastName, String gender, String password) {
		return userDaoImpl.editUser(Long.valueOf(pkId), tel, firstName, lastName, gender, password);
	}

	@Override
	public long geyAllUserCount() {
		return userDaoImpl.geyAllUserCount();
	}

}
