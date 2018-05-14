package com.wtu.demo.dao;

import com.wtu.demo.model.User;

public interface UserDao {
    public User getUserByEmail(String email);
}
