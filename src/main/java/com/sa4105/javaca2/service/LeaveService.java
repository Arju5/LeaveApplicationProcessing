package com.sa4105.javaca2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveStatus;

public interface LeaveService {
	
	public ArrayList<Leave> findAll();
	public boolean createLeave(Leave Leave);
	public boolean editLeave(Leave Leave);
	public void deleteLeavebyId(Integer id);
//	public ArrayList<Leave> findLeaveByUserid(Integer uid);
	public Leave findLeaveById(Integer id);
	public List<Leave> findLeaveByStatus(LeaveStatus apply,LeaveStatus update);
	public ArrayList<Leave> findLeaveByUserName(String username);
	//public List<Long> findLeaveDuration(LeaveStatus leavestatus);
	
	//Leave status
	public void appliedLeaveApplication(Leave leave);
	public void cancelLeaveApplication(Leave leave);
	public void deletedLeaveApplication(Leave leave);
	public void approvedLeaveApplication(Leave leave);
	public void rejectedLeaveApplication(Leave leave);
	public List<Leave> getLeaveRecordDuringPeriod(LocalDate startdate, LocalDate enddate);
	public int countLeaveByLeaveStatus(LeaveStatus leavestatus);
	public void updatedLeaveApplication(Leave leave);
	public List<Leave> getLeavebyApplyDate(LocalDate applyDate);
	public List<Leave> getLeaveHistory();

	public double getLeaveDuration(Leave leave);

}
