package com.project.dao;

import java.util.List;

import com.project.model.User;

public interface UserDao {
User authenticate(User user);
void updateUser(User user);
User registerUser(User user);
User userdetails(String username);
public List<User> getAllUsers(User user);
List<User> getSentUsers(User user);
}
