package com.sa4105.javaca2.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.LeaveSession;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.model.LeaveType;
import com.sa4105.javaca2.model.PublicHoliday;
import com.sa4105.javaca2.model.Role;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.service.LeaveBalanceService;
import com.sa4105.javaca2.service.LeaveService;
import com.sa4105.javaca2.service.LeaveServiceImpl;
import com.sa4105.javaca2.service.LeaveTypeService;
import com.sa4105.javaca2.service.LeaveTypeServiceImpl;
import com.sa4105.javaca2.service.PublicHolidayService;
import com.sa4105.javaca2.service.RoleService;
import com.sa4105.javaca2.service.RoleServiceImpl;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;

@Controller
@RequestMapping("/dbseed")
public class DBSeeder {
	
	@Autowired
	UserService uservice;
	
	@Autowired
	RoleService rservice;
	
	@Autowired
	LeaveTypeService ltservice;
	
	@Autowired
	LeaveService lservice;
	
	@Autowired
	LeaveBalanceService lbservice;
	
	@Autowired
	PublicHolidayService phservice;
	
	public DBSeeder(UserServiceImpl userviceImpl, RoleServiceImpl rserviceImpl, LeaveTypeServiceImpl ltserviceImpl, LeaveServiceImpl lserviceImpl) {
		this.uservice = userviceImpl;
		this.rservice = rserviceImpl;
		this.ltservice = ltserviceImpl;
		this.lservice = lserviceImpl;
	}
	
	Role role1 = new Role("Admin");
	Role role2 = new Role("Manager");
	Role role3 = new Role("Staff");
	LeaveType leavetype1 = new LeaveType(role1,60,"Medical");
	LeaveType leavetype2 = new LeaveType(role1,14,"Annual");
	LeaveType leavetype3 = new LeaveType(role1,"Compensation");
	LeaveType leavetype4 = new LeaveType(role2,60,"Medical");
	LeaveType leavetype5 = new LeaveType(role2,14,"Annual");
	LeaveType leavetype6 = new LeaveType(role2,"Compensation");
	LeaveType leavetype7 = new LeaveType(role3,60,"Medical");
	LeaveType leavetype8 = new LeaveType(role3,18,"Annual");
	LeaveType leavetype9 = new LeaveType(role3,"Compensation");
	User user1 = new User("1234","Khiong Kiat", "Chua",role1,"darell1");
	User user2 = new User("1234","Yamone", "Shwe",role2,"yamone");
	User user3 = new User("1234","Rohan", "Mhatre",role3,"rohan");
	User user4 = new User("1234","Sheryl", "Teo", role1,"sheryl");
	Leave leave1 = new Leave(user1, LocalDate.of(2020, 07, 02), LocalDate.of(2020, 07, 03), LocalDate.of(2020, 06, 30), "Head ache", leavetype1, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	Leave leave2 = new Leave(user1, LocalDate.of(2020, 07, 06), LocalDate.of(2020, 07, 07), LocalDate.of(2020, 06, 30), "Stomach pain", leavetype2, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	Leave leave3 = new Leave(user1, LocalDate.of(2020, 07, 13), LocalDate.of(2020, 07, 15), LocalDate.of(2020, 06, 30), "Vacation", leavetype3, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	Leave leave4 = new Leave(user2, LocalDate.of(2020, 07, 17), LocalDate.of(2020, 07, 17), LocalDate.of(2020, 06, 30), "Outing", leavetype4, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	Leave leave5 = new Leave(user2, LocalDate.of(2020, 07, 17), LocalDate.of(2020, 07, 17), LocalDate.of(2020, 06, 30), "Outing", leavetype5, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	Leave leave6 = new Leave(user2, LocalDate.of(2020, 07, 17), LocalDate.of(2020, 07, 17), LocalDate.of(2020, 06, 30), "Outing", leavetype6, LeaveSession.AM, LeaveSession.PM, LeaveStatus.APPLIED);
	LeaveBalance leaveBalance1 = new LeaveBalance(user1, leavetype1, leavetype1.getMaxLeaveDuration());
	LeaveBalance leaveBalance2 = new LeaveBalance(user1, leavetype2, leavetype2.getMaxLeaveDuration());
	LeaveBalance leaveBalance3 = new LeaveBalance(user1, leavetype3, leavetype3.getMaxLeaveDuration());
	LeaveBalance leaveBalance4 = new LeaveBalance(user2, leavetype4, leavetype4.getMaxLeaveDuration());
	LeaveBalance leaveBalance5 = new LeaveBalance(user2, leavetype5, leavetype5.getMaxLeaveDuration());
	LeaveBalance leaveBalance6 = new LeaveBalance(user2, leavetype6, leavetype6.getMaxLeaveDuration());
	LeaveBalance leaveBalance7 = new LeaveBalance(user4, leavetype1, 60);
	LeaveBalance leaveBalance8 = new LeaveBalance(user4, leavetype2, 12);
	LeaveBalance leaveBalance9 = new LeaveBalance(user4, leavetype3, (long) 2.5);
	LeaveBalance leaveBalance10 = new LeaveBalance(user3, leavetype7, leavetype7.getMaxLeaveDuration());
	LeaveBalance leaveBalance11 = new LeaveBalance(user3, leavetype8, leavetype8.getMaxLeaveDuration());
	LeaveBalance leaveBalance12 = new LeaveBalance(user3, leavetype9, 3.5);
	PublicHoliday publicholiday1 = new PublicHoliday("Christmas",LocalDate.of(2020, 12, 25));
	PublicHoliday publicholiday2 = new PublicHoliday("National Day",LocalDate.of(2020, 8, 10));
	PublicHoliday publicholiday3 = new PublicHoliday("Labor Day",LocalDate.of(2020, 4, 01));
	
	@GetMapping("")
	public String Main(@ModelAttribute("user") User user) {
		rservice.createRole(role1);
		rservice.createRole(role2);
		rservice.createRole(role3);
		ltservice.createLeaveType(leavetype1);
		ltservice.createLeaveType(leavetype2);
		ltservice.createLeaveType(leavetype3);
		ltservice.createLeaveType(leavetype4);
		ltservice.createLeaveType(leavetype5);
		ltservice.createLeaveType(leavetype6);
		ltservice.createLeaveType(leavetype7);
		ltservice.createLeaveType(leavetype8);
		ltservice.createLeaveType(leavetype9);

		uservice.createUser(user1);
		uservice.createUser(user2);
		uservice.createUser(user3);
		uservice.createUser(user4);
		lservice.createLeave(leave1);
		lservice.createLeave(leave2);
		lservice.createLeave(leave3);
		lservice.createLeave(leave4);
		lservice.createLeave(leave5);
		lservice.createLeave(leave6);
		
		lbservice.createLeaveBalance(leaveBalance1);
		lbservice.createLeaveBalance(leaveBalance2);
		lbservice.createLeaveBalance(leaveBalance3);
		lbservice.createLeaveBalance(leaveBalance4);
		lbservice.createLeaveBalance(leaveBalance5);
		lbservice.createLeaveBalance(leaveBalance6);
		lbservice.createLeaveBalance(leaveBalance7);
		lbservice.createLeaveBalance(leaveBalance8);
		lbservice.createLeaveBalance(leaveBalance9);
		lbservice.createLeaveBalance(leaveBalance10);
		lbservice.createLeaveBalance(leaveBalance11);
		lbservice.createLeaveBalance(leaveBalance12);

		phservice.createPublicHoliday(publicholiday1);
		phservice.createPublicHoliday(publicholiday2);
		phservice.createPublicHoliday(publicholiday3);
		lbservice.createLeaveBalance(leaveBalance10);
		lbservice.createLeaveBalance(leaveBalance11);
		lbservice.createLeaveBalance(leaveBalance12);
		user = new User();
		return "forward:/";
	}
	
	
}
