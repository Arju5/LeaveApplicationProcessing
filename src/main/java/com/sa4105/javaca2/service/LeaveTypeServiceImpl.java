package com.sa4105.javaca2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.LeaveType;
import com.sa4105.javaca2.model.Role;
import com.sa4105.javaca2.repo.LeaveRepository;
import com.sa4105.javaca2.repo.LeaveTypeRepository;
import com.sa4105.javaca2.repo.RoleRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Autowired
	LeaveRepository lrepo;
	
	@Autowired
	RoleRepository rrepo;

	@Override
	public ArrayList<LeaveType> findAll() {
		ArrayList<LeaveType> list = (ArrayList<LeaveType>)ltrepo.findAll();
		return list;
	}
	
	@Override
	public ArrayList<String> findAllLeaveTypeNames() {
		ArrayList<String> ltlist = (ArrayList<String>)ltrepo.findAllLeaveTypeNames();
		return ltlist;
	}
	
	@Override
	public boolean createLeaveType(LeaveType leaveType) {
		if (ltrepo.save(leaveType)!= null) return true;
		return false;
	}

	@Override
	public boolean editLeaveType(LeaveType leaveType) {
		if (ltrepo.save(leaveType)!= null) return true;
		return false;
	}

	@Override
	public void deleteLeaveType(LeaveType leaveType) {
		
	}

	@Override
	public ArrayList<String> findLeaveTypeNamesByRoleId(int id) {
		Role role = rrepo.findById(id).get();
		ArrayList<String> leaveTypeNames = ltrepo.findLeaveTypeNamesByRole(role);
		return leaveTypeNames;
	}

	@Override
	public LeaveType findLeaveTypeByNameandRoleId(String leaveTypeName, int roleid) {
		
		return ltrepo.findLeaveTypeByNameandRoleId(leaveTypeName, roleid).get(0);
	}

	@Override
	public Object findLeaveTypeNamesByRole() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
