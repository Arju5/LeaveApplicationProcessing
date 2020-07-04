package com.sa4105.javaca2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa4105.javaca2.model.Attendance;
import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.User;

public interface AttendanceRepository extends JpaRepository<User, Integer> {
	
	@Query("Select a from Attendance a where a.user.username = :username")
	 List<Attendance> findAttendancesByUserName(@Param("username") String username);
	
	


}
