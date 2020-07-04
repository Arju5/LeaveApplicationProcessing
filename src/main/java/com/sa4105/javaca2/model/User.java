package com.sa4105.javaca2.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sa4105.javaca2.model.Department;
import com.sun.istack.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@NotNull
	private String password;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String address;
	private String nric;
	private String homeNumber;
	private String mobileNumber;
	private int postcode;
	private String city;
	private Gender gender;

	@OneToOne
	private Role role;
	private int SupervisedBy;
	private int countryCode;
	private Employment employment;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate workStartDate;
	
	@ManyToOne
	private Department department;
	
	@NotNull
	private String username;
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String password, Role role, String username) {
		super();
		this.password = password;
		this.role = role;
		this.username = username;
	}

	public User(String password, String emailAddress, String homeNumber, String mobileNumber, String firstName, String lastName,
			String address, Role role, int supervisedBy, Department department, int countryCode, Employment employment,
			LocalDate workStartDate, String username, String nric, LocalDate dateOfBirth, int postcode, String city) {
		super();
		this.password = password;
		this.emailAddress = emailAddress;
		this.homeNumber = homeNumber;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.role = role;
		SupervisedBy = supervisedBy;
		this.department = department;
		this.countryCode = countryCode;
		this.employment = employment;
		this.workStartDate = workStartDate;
		this.username = username;
		this.nric = nric;
		this.dateOfBirth = dateOfBirth;
		this.postcode = postcode;
		this.city = city;
	}
	
	

	public User(String password, String firstName, String lastName, Role role, String username) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.username = username;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getSupervisedBy() {
		return SupervisedBy;
	}

	public void setSupervisedBy(int supervisedBy) {
		SupervisedBy = supervisedBy;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	public LocalDate getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(LocalDate workStartDate) {
		this.workStartDate = workStartDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ "]";
	}

	public void setCity(String city) {
		this.city = city;
	}
}
