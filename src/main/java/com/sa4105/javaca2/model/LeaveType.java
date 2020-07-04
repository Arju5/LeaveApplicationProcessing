package com.sa4105.javaca2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leavetypes")
public class LeaveType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@ManyToOne
	private Role role;
	private long maxLeaveDuration;
	private String leaveTypeName;
	
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveType(Role role, long maxLeaveDuration, String leaveTypeName) {
		super();
		this.role = role;
		this.maxLeaveDuration = maxLeaveDuration;
		this.leaveTypeName = leaveTypeName;
	}

	public LeaveType(Role role, String leaveTypeName) {
		super();
		this.role = role;
		this.leaveTypeName = leaveTypeName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getMaxLeaveDuration() {
		return maxLeaveDuration;
	}

	public void setMaxLeaveDuration(long maxLeaveDuration) {
		this.maxLeaveDuration = maxLeaveDuration;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	@Override
	public String toString() {
		return "LeaveType [Id=" + Id + ", role=" + role.getId() + ", maxLeaveDuration=" + maxLeaveDuration + ", leaveTypeName="
				+ leaveTypeName + "]";
	}
	
	
}
