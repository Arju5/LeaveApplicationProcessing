package com.sa4105.javaca2.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "leaves")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate leaveStartDate;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate leaveEndDate;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate applyLeaveDate;
	
	@NotEmpty
	private String leaveReason;
	private int standInStaff;
	@ManyToOne
	private LeaveType leaveType;
	@Enumerated(EnumType.STRING)
	private LeaveSession startLeaveSession;
	@Enumerated(EnumType.STRING)
	private LeaveSession endLeaveSession;
	
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus;
	
	private String leaveComments;
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
	@Transient
	private String startSession;
	
	@Transient
	private String endSession;
	
	@Transient
	private String leaveTypeName;
	
	
	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getStartSession() {
		return startSession;
	}

	public void setStartSession(String startSession) {
		this.startSession = startSession;
	}

	public String getEndSession() {
		return endSession;
	}

	public void setEndSession(String endSession) {
		this.endSession = endSession;
	}

	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(User user, LocalDate leaveStartDate, LocalDate leaveEndDate, LocalDate applyLeaveDate,
			String leaveReason, LeaveType leaveType, LeaveSession startLeaveSession, LeaveSession endLeaveSession,
			LeaveStatus leaveStatus) {
		super();
		this.user = user;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.applyLeaveDate = applyLeaveDate;
		this.leaveReason = leaveReason;
		this.leaveType = leaveType;
		this.startLeaveSession = startLeaveSession;
		this.endLeaveSession = endLeaveSession;
		this.leaveStatus = leaveStatus;
	}



	public Leave(User user, LocalDate leaveStartDate, LocalDate leaveEndDate, LocalDate applyLeaveDate,
			String leaveReason, int standInStaff, LeaveType leaveType, LeaveSession startLeaveSession,
			LeaveSession endLeaveSession, LeaveStatus leaveStatus, String leaveComments) {
		super();
		this.user = user;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.applyLeaveDate = applyLeaveDate;
		this.leaveReason = leaveReason;
		this.standInStaff = standInStaff;
		this.leaveType = leaveType;
		this.startLeaveSession = startLeaveSession;
		this.endLeaveSession = endLeaveSession;
		this.leaveStatus = leaveStatus;
		this.leaveComments = leaveComments;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public LocalDate getApplyLeaveDate() {
		return applyLeaveDate;
	}

	public void setApplyLeaveDate(LocalDate applyLeaveDate) {
		this.applyLeaveDate = applyLeaveDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public int getStandInStaff() {
		return standInStaff;
	}

	public void setStandInStaff(int standInStaff) {
		this.standInStaff = standInStaff;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LeaveSession getStartLeaveSession() {
		return startLeaveSession;
	}

	public void setStartLeaveSession(LeaveSession startLeaveSession) {
		this.startLeaveSession = startLeaveSession;
	}

	public LeaveSession getEndLeaveSession() {
		return endLeaveSession;
	}

	public void setEndLeaveSession(LeaveSession endLeaveSession) {
		this.endLeaveSession = endLeaveSession;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveComments() {
		return leaveComments;
	}

	public void setLeaveComments(String leaveComments) {
		this.leaveComments = leaveComments;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Leave [user=" + user.getMobileNumber() + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate
				+ ", applyLeaveDate=" + applyLeaveDate + ", leaveReason=" + leaveReason + ", startLeaveSession="
				+ startLeaveSession + ", endLeaveSession=" + endLeaveSession + "]";
	}


	
	
}
