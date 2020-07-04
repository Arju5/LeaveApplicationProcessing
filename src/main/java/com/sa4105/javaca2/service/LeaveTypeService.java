package com.sa4105.javaca2.service;

import java.util.ArrayList;
import java.util.List;

import com.sa4105.javaca2.model.LeaveType;

public interface LeaveTypeService {
	
	public ArrayList<LeaveType> findAll();
	public ArrayList<String> findAllLeaveTypeNames();
	public boolean createLeaveType(LeaveType leaveType);
	public boolean editLeaveType(LeaveType leaveType);
	public void deleteLeaveType(LeaveType leaveType);
	public List<String> findLeaveTypeNamesByRoleId(int id);
	public LeaveType findLeaveTypeByNameandRoleId(String leaveTypeName, int roleId);
	public Object findLeaveTypeNamesByRole();
	
}
