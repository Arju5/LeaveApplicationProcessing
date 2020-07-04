package com.sa4105.javaca2.model;

import java.time.LocalTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String roleName;
	private Day day;
	private LocalTime startWorkingHour;
	private LocalTime endWorkingHour;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String roleName, Day day, LocalTime startWorkingHour, LocalTime endWorkingHour) {
		super();
		this.roleName = roleName;
		this.day = day;
		this.startWorkingHour = startWorkingHour;
		this.endWorkingHour = endWorkingHour;
	}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public LocalTime getStartWorkingHour() {
		return startWorkingHour;
	}

	public void setStartWorkingHour(LocalTime startWorkingHour) {
		this.startWorkingHour = startWorkingHour;
	}

	public LocalTime getEndWorkingHour() {
		return endWorkingHour;
	}

	public void setEndWorkingHour(LocalTime endWorkingHour) {
		this.endWorkingHour = endWorkingHour;
	}

	@Override
	public String toString() {
		return "Role [Id=" + Id + ", roleName=" + roleName + ", day=" + day + ", startWorkingHour=" + startWorkingHour
				+ ", endWorkingHour=" + endWorkingHour + "]";
	}
	
	
	
	
	

}
