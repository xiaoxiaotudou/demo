package com.wtu.demo.service.impl;

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
        return user.getUserName().equals(email) && user.getPassword().equals(password);
    }

}
