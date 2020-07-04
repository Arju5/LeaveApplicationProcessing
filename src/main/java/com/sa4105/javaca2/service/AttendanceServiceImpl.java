package com.sa4105.javaca2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Attendance;
import com.sa4105.javaca2.repo.AttendanceRepository;
import com.sa4105.javaca2.repo.LeaveBalanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	AttendanceRepository arepo;

	@Override
	public ArrayList<Attendance> findAttendanceList(String username) {
		return (ArrayList<Attendance>)arepo.findAttendancesByUserName(username);
	}

	@Override
	public boolean createAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		
	}
	
	




}
