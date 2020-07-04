package com.sa4105.javaca2.service;

import java.util.ArrayList;

import com.sa4105.javaca2.model.Attendance;

public interface AttendanceService {

	public ArrayList<Attendance> findAttendanceList(String username);
	public boolean createAttendance(Attendance attendance);
	public boolean editAttendance(Attendance attendance);
	public void deleteAttendance(Attendance attendance);
}
