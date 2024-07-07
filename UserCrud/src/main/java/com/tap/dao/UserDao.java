package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao {
void addUser(User user);
User getUser(int id);
List<User> getAllUser();
void updateUser(User user);
void deleteUser(int id);
}
