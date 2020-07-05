package com.sa4105.javaca2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.LeaveType;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.repo.LeaveBalanceRepository;
import com.sa4105.javaca2.repo.LeaveTypeRepository;
import com.sa4105.javaca2.repo.RoleRepository;
import com.sa4105.javaca2.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository urepo;
	@Autowired
	LeaveBalanceRepository lbrepo;
	@Autowired
	RoleRepository r_repo;
	@Autowired
	LeaveTypeRepository lrepo;
	
	@Override
	public ArrayList<User> findAll() {
		ArrayList<User> list = (ArrayList<User>)urepo.findAll();
		return list;
	}

	@Override
	public boolean createUser(User user) { 
		if (urepo.save(user)!=null) 
			return true;
		else
			return false;
	}

	@Override
	public boolean editUser(User user) {
		if (urepo.save(user)!=null)
			return true;
		else
			return false;
	}

	@Override
	public void deleteUser(User user) {
		urepo.delete(user);
	}

	@Override
	public User findUserByFirstName(String firstName) {
		ArrayList<User> list = (ArrayList<User>) urepo.findByFirstName(firstName);
		return list.get(0);
	}

	@Override
	public User findUserByUsername(String username) {
		ArrayList<User> list = (ArrayList<User>) urepo.findByUsername(username);
		return list.get(0);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = urepo.findByUsernameAndPassword(username, password);
		return user;
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public User findUserById(int id) {
		return urepo.findById(id);
	}

	@Override
	public Integer findUserCount() {
		// TODO Auto-generated method stub
		Integer u_count = urepo.findAll().size();
		return u_count;
	}

	@Override
	public Integer findRoleCount() {
		// TODO Auto-generated method stub
		Integer r_count = r_repo.findAll().size();
		return r_count;
	}

	@Override
	public Integer findLeaveTypeCount() {
		// TODO Auto-generated method stub
		Integer ltype_count = lrepo.findAllLeaveTypeNames().size();
		return ltype_count;
	}



}
