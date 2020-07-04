package com.sa4105.javaca2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leavebalances")
public class LeaveBalance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@ManyToOne
	private User user;
	@OneToOne
	private LeaveType leaveType;
	private double leaveQuantity;

	
	public LeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LeaveBalance(User user, LeaveType leaveType, double leaveQuantity) {
		super();
		this.user = user;
		this.leaveType = leaveType;
		this.leaveQuantity = leaveQuantity;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public LeaveType getLeaveType() {
		return leaveType;
	}


	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}


	public double getLeaveQuantity() {
		return leaveQuantity;
	}


	public void setLeaveQuantity(double leaveQuantity) {
		this.leaveQuantity = leaveQuantity;
	}


	@Override
	public String toString() {
		return "LeaveBalance [Id=" + Id + ", user=" + user + ", leaveType=" + leaveType + ", leaveQuantity="
				+ leaveQuantity + "]";
	}
	
	
	
	
	

}
