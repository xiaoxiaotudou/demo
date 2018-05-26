package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.User;

public interface UserDao {
    public User getUserByEmail(String email);
    public List<User> getAllUserByPagination(Long index, Long pageSize);
    public long geyAllUserCount();
    public User getUserById(Long id);
    public boolean deleteUserById(Long id);
    public boolean createUser(String userName, String tel, String firstName, String lastName, String gender, String password);
    public boolean editUser(Long pkId, String tel, String firstName, String lastName, String gender, String password);
}
