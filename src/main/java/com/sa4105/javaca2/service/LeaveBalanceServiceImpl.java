package com.sa4105.javaca2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.repo.LeaveBalanceRepository;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

	@Autowired
	LeaveBalanceRepository lbrepo;

	@Override
	public ArrayList<LeaveBalance> findAll() {
		ArrayList<LeaveBalance> leavebalances = (ArrayList<LeaveBalance>)lbrepo.findAll();
		return leavebalances;
	}

	@Override
	public boolean createLeaveBalance(LeaveBalance leavebalance) {
		if (lbrepo.save(leavebalance)!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean editLeaveBalance(LeaveBalance leavebalance) {
		if (lbrepo.save(leavebalance)!=null)
			return true;
		else
			return false;
	}

	@Override
	public void deleteLeaveBalance(LeaveBalance leavebalance) {
		lbrepo.delete(leavebalance);
	}

	@Override
	public LeaveBalance findLeaveBalanceById(int id) {
		return lbrepo.findById(id).get();
	}

	@Override
	public ArrayList<LeaveBalance> findLeaveBalanceByUser(User user) {
		return lbrepo.findLeaveBalanceByUser(user);
	}

	@Override
	public LeaveBalance findLeaveBalanceByUsernameAndLeaveType(String username, String leaveTypeName) {
		return lbrepo.findLeaveBalanceByUsernameAndLeaveType(username,leaveTypeName);
	}

	@Override
	public void ReduceLeaveBalanceQty(int leavetypeid, int userid, double days) {
		// TODO Auto-generated method stub
		System.out.println("Leave Type Id - " + leavetypeid + " User Id -" + userid);
		double leaveqty;
		LeaveBalance leavebalance = lbrepo.findLeaveBalance(leavetypeid, userid);
		//LeaveBalance leavebalance = lbrepo.findLeaveBalance(leavetypeid, userid);
		
		if (leavebalance != null ) {
			if(leavebalance.getLeaveQuantity() != 0) {
				leaveqty = leavebalance.getLeaveQuantity() - days;
			}
			else {
				leaveqty = 0;
			}
		} else {
			leaveqty = 0;
		}
		
		System.out.println("Leave Balance - " + leavebalance);
		leavebalance.setLeaveQuantity(leaveqty);
		lbrepo.save(leavebalance);	
		
	}

	@Override
	public LeaveBalance findLeaveBalanceByUserIdandLeaveTypeId(int userid, int leavetypeid) {
		System.out.println(" List of Leave Balances - "+lbrepo.findLeaveBalance(leavetypeid, userid));
		if (lbrepo.findLeaveBalance(leavetypeid, userid) != null ) {
			return lbrepo.findLeaveBalance(leavetypeid, userid);
		}
		return null;
	}

	@Override
	public boolean saveLeaveBalance(LeaveBalance leavebalance) {
		if(lbrepo.save(leavebalance)!=null) return true; else return false;
	}

}
