package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.User;

public interface UserService {
    public User checkSignIn(String email, String password);
    public List<User> getAllUserByPagination(String index, String pageSize);
    public long geyAllUserCount();
    public User getUserById(String id);
    public boolean deleteUserById(String id);
    public boolean createUser(String account, String password, String userName, String gender, String tel);
    public boolean editUser(String pkId, String password, String userName, String gender, String tel);
}