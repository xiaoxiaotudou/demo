package com.wtu.demo.dao;

import java.util.List;

import com.wtu.demo.model.User;

public interface UserDao {
    public User getUserByEmail(String account);
    public List<User> getAllUserByPagination(Long index, Long pageSize);
    public long geyAllUserCount();
    public User getUserById(Long id);
    public boolean deleteUserById(Long id);
    public boolean createUser(String account, String password, String userName, String gender, String tel);
    public boolean editUser(Long pkId, String password, String userName, String gender, String tel);
}
