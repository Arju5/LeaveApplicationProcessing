package com.sa4105.javaca2.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sa4105.javaca2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> { 
	
	@Query("Select r from Role r where r.roleName=:roleName")
	Role findByroleName(String roleName); 
}
