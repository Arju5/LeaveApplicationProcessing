package com.sa4105.javaca2.controller;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.LeaveStatus;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.repo.LeaveRepository;
import com.sa4105.javaca2.service.LeaveBalanceService;
import com.sa4105.javaca2.service.LeaveBalanceServiceImpl;
import com.sa4105.javaca2.service.LeaveService;
import com.sa4105.javaca2.service.LeaveServiceImpl;
import com.sa4105.javaca2.service.LeaveTypeService;
import com.sa4105.javaca2.service.LeaveTypeServiceImpl;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;

import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	LeaveService lservice;

	@Autowired
	UserService uservice;

	@Autowired
	LeaveTypeService ltservice;

	@Autowired
	LeaveBalanceService lbservice;


	public ManagerController(UserServiceImpl userviceImpl,LeaveServiceImpl lserviceImpl, LeaveTypeServiceImpl ltserviceImpl, LeaveBalanceServiceImpl lbserviceImpl) {
		super();
		this.uservice = userviceImpl;
		this.lservice = lserviceImpl;
		this.ltservice = ltserviceImpl;
		this.lbservice = lbserviceImpl;
	}

	@RequestMapping("/{username}")
	public String userDashboardwithLogin(@ModelAttribute("user") User user, @PathVariable ("username") String username,HttpSession session, Model model) {
		System.out.println("------------");
		System.out.println("This is inside /manager/{username} mapping ");
		System.out.println("This user object's username is " + user.getUsername());
		System.out.println("This user object's session's username is " + session.getAttribute("username"));
		System.out.println("This user object's session's role is " + session.getAttribute("role"));

		if (session.getAttribute("role").equals("Manager")) {
			int appliedCount = lservice.countLeaveByLeaveStatus(LeaveStatus.APPLIED);
			int approvedCount = lservice.countLeaveByLeaveStatus(LeaveStatus.APPROVED);
			//List<Leave> llist = new ArrayList<Leave>();
			model.addAttribute("pendingleavecount",appliedCount);
			model.addAttribute("approvedleavecount",approvedCount);
			List<Leave> leavehistory = lservice.getLeaveHistory();
			model.addAttribute("elist",leavehistory);
			return "indexManager";
		}
		else 
			return "redirect:/";
	}

	@GetMapping("/{username}/report")
	public String ReportGeneration(Model model) {
		ArrayList<Leave> list = (ArrayList<Leave>) lservice.findAll();
		model.addAttribute("llist", list);
		return "report";
	}

	@GetMapping("/{username}/viewappliedlist")
	public String ViewPendingList(Model model) {
		model.addAttribute("plist", lservice.findLeaveByStatus(LeaveStatus.APPLIED,LeaveStatus.UPDATED));
		return "leavependinglist";
	}

	@RequestMapping("/{username}/leaveapprovalform/{id}")
	public String ManageLeave(@PathVariable("id") Integer id, Model model) {
		
		LocalDate s_date, e_date;
		Leave leave = lservice.findLeaveById(id);
		model.addAttribute("leave", leave);
		s_date = leave.getLeaveStartDate();
		e_date = leave.getLeaveEndDate();
		model.addAttribute("elist",lservice.getLeaveRecordDuringPeriod(s_date,e_date));
		return "leaveapprovalform";
	}

	@RequestMapping("/{username}/approveleave/{id}/{comment}")
	public String manageapprovestatus(@PathVariable("id") Integer id,@PathVariable("comment") String comment, HttpSession session) {
		if(id != 0) {
			String mgercomment;
			Integer leavetype_id,userid;
			String leavetype;
			LocalDate startdate, enddate;

			Leave leave = lservice.findLeaveById(id);
			leavetype_id = leave.getLeaveType().getId();
			userid = leave.getUser().getId();
			System.out.println("User Id - " + leave.getUser().getId());
			if(comment == null) {
				mgercomment = "";
			}
			else
			{
				mgercomment = comment;
			}
			System.out.println(leave);
			startdate = leave.getLeaveStartDate();
			enddate = leave.getLeaveEndDate().plusDays(1);
			long days = ChronoUnit.DAYS.between(startdate, enddate);
			System.out.println(days);
			leave.setLeaveComments(mgercomment);
			//lbservice.ReduceLeaveBalanceQty(leavetype_id, userid, days);
			lservice.approvedLeaveApplication(leave);

			return "redirect:/manager/" + session.getAttribute("username");
		}
		return "redirect:/manager/" + session.getAttribute("username");
	}

	@GetMapping("/{username}/cancelleave/{id}/{comment}")
	public String managerejectstatus(@PathVariable("id") Integer id,@PathVariable("comment") String comment) {
		if(id != 0) {
			Leave leave = lservice.findLeaveById(id);
			leave.setLeaveComments(comment);
			Double leaveduration = lservice.getLeaveDuration(leave);
			LeaveBalance leavebalance =  lbservice.findLeaveBalanceByUserIdandLeaveTypeId(leave.getUser().getId(), leave.getLeaveType().getId());
			if (leave.getLeaveType().getLeaveTypeName() == "Compensation") {
				leavebalance.setLeaveQuantity(leavebalance.getLeaveQuantity()+leaveduration);
			} else {
				leavebalance.setLeaveQuantity(leavebalance.getLeaveQuantity()+Math.abs(leaveduration));
			}
			lbservice.saveLeaveBalance(leavebalance);
			lservice.rejectedLeaveApplication(leave);
			return "leavependinglist";
		}
		return null;
	}
	
	@GetMapping("/{username}/leavechoose")
	public String getleave(Model model) {
		//List<Leave> leave = new ArrayList<>();
		//model.addAttribute("elist",leave);
		return "leaveperiod";
	}
	
	
	@GetMapping("/{username}/leaveperiod/{startdate}/{enddate}")
	public String managerejectstatus(@PathVariable("startdate") String startdate,@PathVariable("enddate") String enddate, Model model) {
		
		//AjaxResponseBody result = new AjaxResponseBody();
		
		List<Leave> leave = new ArrayList<>();
		
		LocalDate s_date = LocalDate.parse(startdate);
		LocalDate e_date = LocalDate.parse(enddate);
		
		if(startdate == null || enddate == null) {
			model.addAttribute("elist",leave);
		}
		else {
			model.addAttribute("elist",lservice.getLeaveRecordDuringPeriod(s_date,e_date));
		}
		//return "leavelistbyleaveperiod";
		//return ResponseEntity.ok(model);
		return "leavelistbyleaveperiod :: elist";
		
		
	}
	
	
	
	






}