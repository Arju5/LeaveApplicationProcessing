package com.sa4105.javaca2.service;

import java.util.ArrayList;

import com.sa4105.javaca2.model.User;

public interface UserService {
	
	public ArrayList<User> findAll();
	public boolean createUser(User user);
	public boolean editUser(User user);
	public void deleteUser(User user);
	public User findUserById(int id);
	public User findUserByFirstName(String firstName);
	public User findUserByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	public boolean saveUser(User user);
	public Integer findUserCount();
	public Integer findRoleCount();
	public Integer findLeaveTypeCount();

}
