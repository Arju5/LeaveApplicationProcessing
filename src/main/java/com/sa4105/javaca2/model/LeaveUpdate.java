package com.sa4105.javaca2.model;

import java.time.LocalDate;

//not database table
//just requirement for our controller for leave form to update
public class LeaveUpdate {
	public int id;
	public String leaveType;
	public LocalDate leaveStartDate;
	public LocalDate leaveEndDate;
	public String leaveReason;
	
	//public String standInStaff;
}
