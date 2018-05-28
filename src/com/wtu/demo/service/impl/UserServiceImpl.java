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
    public boolean checkSignIn(String account, String password) {
        User user = userDaoImpl.getUserByEmail(account);
        return user.getAccount() != null
                && user.getPassword() != null
                && user.getAccount().equals(account)
                && user.getPassword().equals(password)
                && user.isAdmin();
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
	public boolean createUser(String account, String password, String userName, String gender, String tel) {
		return userDaoImpl.createUser(account, password, userName, gender, tel);
	}

	@Override
	public boolean editUser(String pkId, String password, String userName, String gender, String tel) {
		return userDaoImpl.editUser(Long.valueOf(pkId), password, userName, gender, tel);
	}

	@Override
	public long geyAllUserCount() {
		return userDaoImpl.geyAllUserCount();
	}

}
