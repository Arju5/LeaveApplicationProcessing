package com.sa4105.javaca2.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sa4105.javaca2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByFirstName(String firstName);
	List<User> findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findById(int id);

}
