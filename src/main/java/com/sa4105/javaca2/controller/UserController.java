package com.sa4105.javaca2.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.LeaveSession;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.model.LeaveType;
import com.sa4105.javaca2.model.LeaveUpdate;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.service.LeaveBalanceService;
import com.sa4105.javaca2.service.LeaveBalanceServiceImpl;
import com.sa4105.javaca2.service.LeaveService;
import com.sa4105.javaca2.service.LeaveServiceImpl;
import com.sa4105.javaca2.service.LeaveTypeService;
import com.sa4105.javaca2.service.LeaveTypeServiceImpl;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private LeaveService lservice;

	@Autowired
	private UserService uservice;

	@Autowired
	private LeaveTypeService ltservice;

	@Autowired
	private LeaveBalanceService lbservice;

	public UserController(UserServiceImpl userviceImpl,LeaveServiceImpl lserviceImpl, LeaveTypeServiceImpl ltserviceImpl, LeaveBalanceServiceImpl lbserviceImpl) {
		super();
		this.uservice = userviceImpl;
		this.lservice = lserviceImpl;
		this.ltservice = ltserviceImpl;
		this.lbservice = lbserviceImpl;
	}

	@RequestMapping("/{username}")
	public String userDashboardwithLogin(@ModelAttribute("user") @Valid User user, BindingResult bindingresult,
			@PathVariable("username") String username, HttpSession session, Model model) {
		System.out.println("------------");
		System.out.println("This is inside /user/{username} mapping ");
		System.out.println("This user object's username is " + user.getUsername());

		if (user.getUsername().equals(username)) {
			model.addAttribute("leaves", lservice.findLeaveByUserName(username));
			model.addAttribute("leavebalanceAnnual", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Annual").getLeaveQuantity()));
			model.addAttribute("leavebalanceMedical", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Medical").getLeaveQuantity()));
			model.addAttribute("leavebalanceCompensation",
					lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Compensation").getLeaveQuantity());
			return "indexEmployee";
		} else
			return "redirect:/";
	}

	@RequestMapping("/{username}/leave")
	public String ApplyLeavePage(@PathVariable("username") String username, HttpSession session, Model model) {
		System.out.println("I'm in Apply Leave Page method");
		Leave leave = new Leave();
		User user = (User)session.getAttribute("user");
		leave.setUser(user);
		model.addAttribute("leave", leave);
		model.addAttribute("leaveTypes", ltservice.findLeaveTypeNamesByRoleId((int)session.getAttribute("roleid")));
		model.addAttribute("leaveBalances", lbservice.findLeaveBalanceByUser(user));
		System.out.println(session.getAttribute("username"));
		return "leaveform";
	}
	
	@RequestMapping("/{username}/leave/save")
	public String SaveLeave(@ModelAttribute @Valid Leave leave, BindingResult bindingResult, @PathVariable("username") String username, HttpSession session, Model model) {
		System.out.println(bindingResult.getAllErrors());
		
		System.out.println(leave.getStartDate());
		leave.setLeaveStartDate(LocalDate.parse(leave.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		leave.setLeaveEndDate(LocalDate.parse(leave.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		leave.setApplyLeaveDate(LocalDate.now());
		leave.setStartLeaveSession(LeaveSession.valueOf(leave.getStartSession()));
		leave.setEndLeaveSession(LeaveSession.valueOf(leave.getEndSession()));
		leave.setUser((User)session.getAttribute("user"));
		leave.setLeaveType(ltservice.findLeaveTypeByNameandRoleId(leave.getLeaveType().getLeaveTypeName(), (int)session.getAttribute("roleid")));
		leave.setLeaveStatus(LeaveStatus.APPLIED);
		System.out.println(lservice.getLeaveDuration(leave));
		Double leaveduration = lservice.getLeaveDuration(leave);
		LeaveBalance leavebalance =  lbservice.findLeaveBalanceByUserIdandLeaveTypeId(leave.getUser().getId(), leave.getLeaveType().getId());
		if (leaveduration <= leavebalance.getLeaveQuantity() && leaveduration > 0) {
			System.out.println("The leave duration is - " + leaveduration);
			System.out.println("Leave Type name " + leave.getLeaveType().getLeaveTypeName());
			System.out.println(" Fraction - " + leaveduration%1);
			System.out.println((!(leave.getLeaveType().getLeaveTypeName().equals("Compensation")) && (leaveduration%1) != 0));
			if(bindingResult.hasErrors() || leaveduration == 0.0 || ((!(leave.getLeaveType().getLeaveTypeName().equals("Compensation")) && (leaveduration%1) != 0))) {
				model.addAttribute("errormessage", "Select proper input Date and Session");
				return "forward:/user/" + leave.getUser().getUsername() + "/leave";
			}
			System.out.println("Before Leave Balance");
			
			if (leave.getLeaveType().getLeaveTypeName().equals("Compensation")) {
				leavebalance.setLeaveQuantity(leavebalance.getLeaveQuantity()-leaveduration);
			} else {
				leavebalance.setLeaveQuantity(leavebalance.getLeaveQuantity()-Math.abs(leaveduration));
			}
			lbservice.saveLeaveBalance(leavebalance);
			if (lservice.createLeave(leave)) {
				model.addAttribute("leaves", lservice.findLeaveByUserName((String)session.getAttribute("username")));
				model.addAttribute("leavebalanceAnnual", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Annual").getLeaveQuantity()));
				model.addAttribute("leavebalanceMedical", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Medical").getLeaveQuantity()));
				model.addAttribute("leavebalanceCompensation",
						lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Compensation").getLeaveQuantity());
				return "leavelist";
			} else {
				model.addAttribute("leave",	leave);
				return "leaveform";
			}	
		} else if (leaveduration > 0){
			model.addAttribute("errormessage", "Input leave period cannot be more than the available Leave balance");
			return "forward:/user/" + leave.getUser().getUsername() + "/leave";
		} else {
			model.addAttribute("errormessage", "Leave period is invalid.");
			return "forward:/user/" + leave.getUser().getUsername() + "/leave";
		}
		
	}

	@GetMapping("/{username}/leavelist")
	public String LeaveList(@PathVariable("username") String username, Model model) {
		model.addAttribute("leavebalanceAnnual", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Annual").getLeaveQuantity()));
		model.addAttribute("leavebalanceMedical", Math.round(lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Medical").getLeaveQuantity()));
		model.addAttribute("leavebalanceCompensation",
				lbservice.findLeaveBalanceByUsernameAndLeaveType(username, "Compensation").getLeaveQuantity());
		model.addAttribute("leaves", lservice.findLeaveByUserName(username));
		return "leavelist";
	}

	@GetMapping("/{username}/leavedetails/{id}")
	public String LeaveDetails(@PathVariable("id") Integer id, Model model) {

		ArrayList<String> ltlist = ltservice.findAllLeaveTypeNames();
		model.addAttribute("ltypes", ltlist);
		
		model.addAttribute("leavedetail", lservice.findLeaveById(id));
		
		return "LeaveDetails";
	}

	@RequestMapping(value = "/{username}/updateleave", method = RequestMethod.POST)
	public @ResponseBody String UpdateLeave(@PathVariable("username") String username, @RequestBody LeaveUpdate leaveUpdate,
			HttpSession session) {
		
		System.out.println("Leave Update with Json");
		
		int roleid = (int)session.getAttribute("roleid");
		LeaveType ltype = ltservice.findLeaveTypeByNameandRoleId(leaveUpdate.leaveType, roleid);
		System.out.println("Ltype::  "+ltype.getId());
		
		Leave l = lservice.findLeaveById(leaveUpdate.id);
		l.setLeaveType(ltype);
		l.setLeaveStartDate(leaveUpdate.leaveStartDate);
		l.setLeaveEndDate(leaveUpdate.leaveEndDate);
		l.setLeaveReason(leaveUpdate.leaveReason);
		//System.out.println(l.getLeaveType().getLeaveTypeName());
		lservice.editLeave(l);
		lservice.updatedLeaveApplication(l);
		System.out.println("Saved");
		
		ObjectMapper om = new ObjectMapper();
		String ss = null;
		try {
			ss = om.writeValueAsString("success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ss;
	}
	
	@RequestMapping(value = "/{username}/deleteLeave/{id}")
	public String deleteLeave(@PathVariable("username") String username,@PathVariable("id") Integer id) {
		lservice.deletedLeaveApplication(lservice.findLeaveById(id));
		return "forward:/user/" + username+ "/leavelist";
	}

	@RequestMapping(value = "/{username}/cancelLeave/{id}")
	public String cancelLeave(@PathVariable("username") String username,@PathVariable("id") Integer id) {
		lservice.cancelLeaveApplication(lservice.findLeaveById(id));
		return "forward:/user/" + username+ "/leavelist";
	}
	
	@GetMapping("")
	public String login(@ModelAttribute ("user") User user, HttpSession session) {
		user = new User();
		System.out.println("This is the GetMapping at HomePage");

		return "index";
	}
}
