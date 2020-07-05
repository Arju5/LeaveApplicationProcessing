package com.sa4105.javaca2.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa4105.javaca2.model.LeaveType;
import com.sa4105.javaca2.model.Role;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

	@Query("SELECT lt.leaveTypeName FROM LeaveType lt where lt.role = :role")
	ArrayList<String> findLeaveTypeNamesByRole (@Param("role") Role role);
	
	@Query("SELECT distinct lt.leaveTypeName from LeaveType lt")
	ArrayList<String> findAllLeaveTypeNames();
	
	@Query("SELECT lt FROM LeaveType lt where lt.leaveTypeName = :ltname AND lt.role.Id = :roleid")
	ArrayList<LeaveType> findLeaveTypeByNameandRoleId (@Param("ltname") String ltname, @Param("roleid") int id);
	
	@Query("SELECT lt FROM LeaveType lt where lt.role = :role")
	ArrayList<LeaveType> findLeaveIdByRole (@Param("role") Role role);
	
	
}
