package com.sa4105.javaca2.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.repo.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	LeaveRepository lrepo;
	
	@Override
	public ArrayList<Leave> findAll() {
		ArrayList<Leave> list = (ArrayList<Leave>)lrepo.findAll();
		return list;
	}

	@Override
	public boolean createLeave(Leave leave) {
		if(lrepo.save(leave)!=null) return true; else return false;
	}

	@Override
	public boolean editLeave(Leave leave) {
		if(lrepo.save(leave)!=null) return true; else return false;
	}

	@Override
	public void deleteLeavebyId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Leave findLeaveById(Integer id) {
		// TODO Auto-generated method stub
		return lrepo.findById(id).get();
	}
	
	@Override
	public List<Leave> findLeaveByStatus(LeaveStatus status) {
		// TODO Auto-generated method stub
		List<Leave> list = lrepo.findByleaveStatus(status);
		return list;
	}
	
	@Transactional
	public ArrayList<Leave> findLeaveByUserName(String username) {
		return (ArrayList<Leave>) lrepo.findLeaveByUserName(username);
	}

	@Override
	public void appliedLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.APPLIED);
		lrepo.save(leave);
	}

	@Override
	public void cancelLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.CANCELED);
		lrepo.save(leave);
	}

	@Override
	public void deletedLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.DELETED);
		lrepo.save(leave);
	}

	@Override
	public void approvedLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.APPROVED);
		lrepo.save(leave);
	}

	@Override
	public void rejectedLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.REJECTED);
		lrepo.save(leave);
	}
	
	@Override
	public void updatedLeaveApplication(Leave leave) {
		// TODO Auto-generated method stub
		leave.setLeaveStatus(LeaveStatus.UPDATED);
		lrepo.save(leave);
	}
	@Override
	public int countLeaveByLeaveStatus(LeaveStatus leaveStatus) {
		int count = lrepo.countLeaveByLeaveStatus(leaveStatus);
		return count;
	}

	@Override
	public List<Leave> getLeaveRecordDuringPeriod(LocalDate startdate, LocalDate enddate) {
		// TODO Auto-generated method stub
		List<Leave> leavelist = lrepo.findLeaveDuringLeavePeriod(startdate,enddate);
		if(leavelist != null) {
			return leavelist;
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Leave> getLeavebyApplyDate(LocalDate applyDate) {
		// TODO Auto-generated method stub
		List<Leave> leavelist = lrepo.findLeavebyApplyDate(applyDate);
		return leavelist;
	}
	
	@Override
	public List<Leave> getLeaveHistory() {
		// TODO Auto-generated method stub
		List<Leave> leavelist = lrepo.findLeaveHistory();
		return leavelist;
	}

	/*
	 * @Override public List<Long> findLeaveDuration(LeaveStatus status) { // TODO
	 * Auto-generated method stub LocalDate startdate; LocalDate enddate;
	 * List<Leave> leavelist = lrepo.findByleaveStatus(status); List<Long> duration
	 * = new ArrayList<>();
	 * 
	 * for(int i=0;i<leavelist.size();i++) { startdate =
	 * leavelist.get(i).getLeaveStartDate(); enddate =
	 * leavelist.get(i).getLeaveEndDate().plusDays(1); long days =
	 * ChronoUnit.DAYS.between(startdate, enddate); duration.add(days); } return
	 * duration; }
	 */

}