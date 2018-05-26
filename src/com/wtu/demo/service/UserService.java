package com.wtu.demo.service;

import java.util.List;

import com.wtu.demo.model.User;

public interface UserService {
    public boolean checkSignIn(String email, String password);
    public List<User> getAllUserByPagination(String index, String pageSize);
    public long geyAllUserCount();
    public User getUserById(String id);
    public boolean deleteUserById(String id);
    public boolean createUser(String userName, String tel, String firstName, String lastName, String gender, String password);
    public boolean editUser(String pkId, String tel, String firstName, String lastName, String gender, String password);
}