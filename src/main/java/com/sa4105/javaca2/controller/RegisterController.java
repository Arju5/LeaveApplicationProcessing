package com.sa4105.javaca2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sa4105.javaca2.repo.LeaveRepository;
import com.sa4105.javaca2.service.LeaveService;
import com.sa4105.javaca2.service.LeaveServiceImpl;
import com.sa4105.javaca2.service.LeaveTypeService;
import com.sa4105.javaca2.service.LeaveTypeServiceImpl;
import com.sa4105.javaca2.service.RoleService;
import com.sa4105.javaca2.service.RoleServiceImpl;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;



@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	LeaveService lservice;

	@Autowired
	UserService uservice;

	@Autowired
	LeaveTypeService ltservice;
	
	@Autowired
	RoleService rservice;
	
	public RegisterController(LeaveTypeServiceImpl ltserviceImpl,UserServiceImpl userviceImpl,LeaveServiceImpl lserviceImpl,RoleServiceImpl rserviceImpl){
		super();
		this.uservice = userviceImpl;
		this.lservice = lserviceImpl;
		this.ltservice = ltserviceImpl;
		this.rservice=rserviceImpl;
	}

}
