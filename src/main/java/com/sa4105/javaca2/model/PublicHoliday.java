package com.sa4105.javaca2.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "publicholidays")
public class PublicHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String phName;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate phDate;
	
	@Transient
	private String holidayDate;
	
	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public PublicHoliday() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PublicHoliday(String phName, LocalDate phDate) {
		super();
		this.phName = phName;
		this.phDate = phDate;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPhName() {
		return phName;
	}

	public void setPhName(String phName) {
		this.phName = phName;
	}

	public LocalDate getPhDate() {
		return phDate;
	}

	public void setPhDate(LocalDate phDate) {
		this.phDate = phDate;
	}

	
	

}
