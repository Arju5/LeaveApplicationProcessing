package com.sa4105.javaca2.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.LeaveSession;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.model.PublicHoliday;
import com.sa4105.javaca2.repo.LeaveRepository;
import com.sa4105.javaca2.repo.PublicHolidayRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	LeaveRepository lrepo;
	
	@Autowired
	private PublicHolidayRepository phrepo;
	
	@Autowired
	EmailImpl emailimpl;
	
	
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
	public List<Leave> findLeaveByStatus(LeaveStatus apply,LeaveStatus update) {
		// TODO Auto-generated method stub
		List<Leave> list = lrepo.findStatusApplyUpdate(apply, update);
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
		emailimpl.sendApprovedEmail(leave);
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

	@Override
	public double getLeaveDuration(Leave leave) {
		Double leaveduration=0.0;
		LocalDate startDate = leave.getLeaveStartDate();
		LocalDate endDate = leave.getLeaveEndDate();
		System.out.println(leave.getStartLeaveSession() + " - " + leave.getEndLeaveSession());
		System.out.println(startDate + " - " + endDate);
		// Finding the duration of the two dates including the AM and PM
		if((leave.getStartLeaveSession() == LeaveSession.PM && leave.getEndLeaveSession() == LeaveSession.AM && startDate.isEqual(endDate)) 
				|| startDate.getDayOfWeek().getValue() > 5 || endDate.getDayOfWeek().getValue() > 5)
			return leaveduration;
		else {
			leaveduration = (double)ChronoUnit.DAYS.between(startDate, endDate);
			if(leave.getStartLeaveSession() == LeaveSession.AM) {
			    if (endDate.isAfter(startDate)) {
			    	if (leaveduration > 0 && leave.getEndLeaveSession() == LeaveSession.AM) {
				    	leaveduration += 0.5;
				    } else if (leaveduration > 0 && leave.getEndLeaveSession() == LeaveSession.PM) {
				    	leaveduration += 1;
				    } else if (leaveduration == 0 && leave.getEndLeaveSession() == LeaveSession.PM) {
				    	leaveduration = 1.0;
				    } else if ( leave.getLeaveTypeName() == "Compensation" && leave.getEndLeaveSession() == LeaveSession.AM) {
				    	leaveduration += 0.5;
				    }
			    } else if (startDate.isEqual(endDate)) {
			    	if (leave.getEndLeaveSession() == LeaveSession.AM && leave.getLeaveType().getLeaveTypeName().equals("Compensation"))
			    		leaveduration = 0.5;
			    	else
			    		leaveduration = 1.0;
			    }
			} else if (leave.getStartLeaveSession() == LeaveSession.PM && (endDate.isAfter(startDate))) { 
				if (leave.getEndLeaveSession() == LeaveSession.PM)
					leaveduration += 0.5;
			} else if (leave.getEndLeaveSession() == LeaveSession.PM && leave.getLeaveType().getLeaveTypeName().equals("Compensation")) {
				leaveduration += 0.5;
			}
		}
		// Getting the list of Public Holidays
		List<PublicHoliday> publicHolidays = phrepo.findAll();
		List<LocalDate> phDateList = new ArrayList<LocalDate>();
		for (Iterator<PublicHoliday> iterator = publicHolidays.iterator(); iterator.hasNext();) {
			PublicHoliday publicHoliday = (PublicHoliday) iterator.next();
			phDateList.add(publicHoliday.getPhDate());
		}	
		
		if(leaveduration <= 14) {
			//Checking for weekends and public holidays
			LocalDate iter = startDate;
			do {
				System.out.println(iter);
				// if day found in list of public holidays or weekdays then decrement by one
				if (phDateList.contains(iter) || iter.getDayOfWeek().getValue() == 6 || iter.getDayOfWeek().getValue() == 7)
					leaveduration -= 1;
				iter=iter.plusDays(1);
				System.out.println("Duration - " + leaveduration);
			} while(iter.isBefore(endDate));
		}
		return leaveduration;
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