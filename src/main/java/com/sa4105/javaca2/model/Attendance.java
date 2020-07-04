package com.sa4105.javaca2.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@ManyToOne
	private User user;
	@DateTimeFormat (pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDate checkInTime;
	@DateTimeFormat (pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDate checkOutTime;
	
	
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Attendance (int userId,User user,LocalDate checkInTime,LocalDate checkOutTime) { 
		super();
		this.Id=userId;
		this.user=user;
		this.checkInTime=checkInTime;
		this.checkOutTime=checkOutTime;
	}
	public int getuserId() {
		return Id;
	}

	public void setId(int userId) {
		this.Id = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getcheckInTime() {
		return checkInTime;
	}

	public void setLeaveStartDate(LocalDate checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDate getcheckOutTime() {
		return checkOutTime;
	}

	public void setcheckOutTime(LocalDate checkOutTime) {
		this.checkOutTime = checkOutTime;
	}


}
