package com.sa4105.javaca2.service;

import java.util.ArrayList;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.User;

public interface LeaveBalanceService {

	public ArrayList<LeaveBalance> findAll();
	public boolean createLeaveBalance(LeaveBalance leavebalance);
	public boolean editLeaveBalance(LeaveBalance leavebalance);
	public boolean saveLeaveBalance(LeaveBalance leavebalance);
	public void deleteLeaveBalance(LeaveBalance leavebalance);
	public LeaveBalance findLeaveBalanceById(int id);
	public ArrayList<LeaveBalance> findLeaveBalanceByUser(User user);
	public void ReduceLeaveBalanceQty(int leavetypeid, int userid, double days);
	public LeaveBalance findLeaveBalanceByUsernameAndLeaveType(String username,String leaveTypeName);
	public LeaveBalance findLeaveBalanceByUserIdandLeaveTypeId(int userid, int leavetypeid);
}
