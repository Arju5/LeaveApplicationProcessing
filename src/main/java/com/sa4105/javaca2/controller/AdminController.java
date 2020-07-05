package com.sa4105.javaca2.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sa4105.javaca2.model.PublicHoliday;
import com.sa4105.javaca2.model.Role;
import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.service.LeaveTypeService;
import com.sa4105.javaca2.service.PublicHolidayService;
import com.sa4105.javaca2.service.PublicHolidayServiceImpl;
import com.sa4105.javaca2.service.RoleService;
import com.sa4105.javaca2.service.RoleServiceImpl;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService uservice;
	
	@Autowired
	RoleService rservice;
	
	@Autowired
	LeaveTypeService ltservice;
	
	@Autowired
	PublicHolidayService phservice;
	
	public AdminController(UserServiceImpl userviceImpl, RoleServiceImpl rserviceImpl, PublicHolidayServiceImpl phserviceImpl) {
		this.uservice = userviceImpl;
		this.rservice = rserviceImpl;
		this.phservice = phserviceImpl;
	}

	@RequestMapping("/{username}")
	public String userDashboardwithLogin(@ModelAttribute("user") User user, @PathVariable ("username") String username,HttpSession session, Model model) {
		System.out.println("------------");
		System.out.println("This is inside /admin/{username} mapping ");
		System.out.println("This user object's username is " + user.getUsername());
		System.out.println("This user object's session's username is " + session.getAttribute("username"));
		System.out.println("This user object's session's role is " + session.getAttribute("role"));
		
		if (session.getAttribute("role").equals("Admin")) {
			model.addAttribute("usercount", uservice.findUserCount());
			model.addAttribute("rolecount",uservice.findRoleCount());
			model.addAttribute("leavetypecount",uservice.findLeaveTypeCount());
			model.addAttribute("userlist",uservice.findAll());
			return "indexAdmin";
		}
		else 
			return "redirect:/";
	}
	
	@GetMapping("/{username}/adduser")
	public String addUser(Model model) {
		System.out.println("------------");
		System.out.println("This is inside add user");
		User user = new User();
		ArrayList<Role> rolelist = rservice.findAll();
		model.addAttribute("roles",rolelist);
		model.addAttribute("user",user);
		return "account";
	}
	
//	@PostMapping("/{username}/adduser")
//	public String addUser(Model model, @ModelAttribute("user") User user) {
//		System.out.println("---");
//		System.out.println("This is the /{username}/add user Mapping: the user object have first name of " + user.getFirstName());
//
//		if(user != null) {
//		    System.out.println("");
//			String username = (user.getFirstName() + user.getLastName()).replace(" ","").toLowerCase();
//		    String password = "1234";
//		    user.setUsername(username);
//		    user.setPassword(password);
//		}
//		uservice.createUser(user);
//		model.addAttribute("user",user);
//		return "redirect:/admin/{username}";
//	}
	
	@PostMapping("/{username}/saveuser")
	public String saveUser(@ModelAttribute ("user") @Valid User user, Model model, BindingResult bindingresult, HttpSession session) {
		if(user != null) {
		    System.out.println("");
			String username = (user.getFirstName() + user.getLastName()).replace(" ","").toLowerCase();
		    String password = "1234";
		    user.setUsername(username);
		    user.setPassword(password);
		}
		System.out.println("User id: " + user.getId());
		System.out.println("User saving "+user.getRole().getRoleName());
		Role r =rservice.findRoleByRoleName(user.getRole().getRoleName());
		System.out.println(r.getId());
		user.setRole(r);
		uservice.createUser(user);
		System.out.println("User id: " + user.getId());
		if (user.getId()==0) {
			System.out.println("There is no user id, this is a new user");
			
		}
		model.addAttribute("user",user);
		return "redirect:/admin/{username}" + "/summary";
	}
	
	@PostMapping("/{username}/saverole")
	public String saveRole(@ModelAttribute("user") @Validated User user, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("user", new User());
			return "account";
		}
		uservice.saveUser(user);
		return "indexAdmin";
	}
	
	@GetMapping(value = "/{username}/edituser/{userid}")
	public String editUser(@PathVariable("userid") Integer id, Model model) {
		System.out.println("------------");
		System.out.println("This is inside edit user");
		User user = uservice.findUserById(id);
		ArrayList<Role> rolelist = rservice.findAll();
		model.addAttribute("roles",rolelist);
		model.addAttribute("user", user);
		return "account";
	}
	
	@PostMapping(value = "/{username}/edituser/{userid}")
	public String editUser(@PathVariable("userid") Integer id, @ModelAttribute("user") User user, Model model, HttpSession session) {
		uservice.saveUser(user);
		return "indexAdmin";
	}

	@GetMapping("/{username}/summary")
	public String Summary(Model model,@PathVariable("username") String username) {
		System.out.println("------------");
		System.out.println("This is inside /manager/{username}/summary mapping ");
		ArrayList<User> users = (ArrayList<User>)uservice.findAll();
		model.addAttribute("users",users);
		model.addAttribute("username",username);
		return "viewemployee";
	}
	
	@GetMapping("/{username}/addpublicholiday")
	public String addPublicHoliday(Model model) {
		System.out.println("------------");
		System.out.println("This is inside addpublicholiday");
		PublicHoliday publicholiday = new PublicHoliday();
		model.addAttribute("publicholiday",publicholiday);
		return "publicholiday";
	}
	
	@GetMapping("/{username}/publicholidaysummary")
	public String PublicHolidaySummary(Model model) {
		ArrayList<PublicHoliday> phlist = (ArrayList<PublicHoliday>) phservice.findAll();
		model.addAttribute("phlist", phlist);
		return "publicholidaylist";
	}
	
	@GetMapping("/{username}/editpublicholiday/{id}")
	public String saveRole(@PathVariable("id") Integer id,@PathVariable("username") String username, @ModelAttribute("publicholiday") PublicHoliday publicHoliday, Model model, HttpSession session) {
		publicHoliday = phservice.findById(id);
		System.out.println("This is inside EDITpublicholiday");
		System.out.println(publicHoliday.getPhName());
		System.out.println(publicHoliday.getPhDate());
		model.addAttribute("publicholiday",publicHoliday);
		return "publicholiday";
	}
	
	@RequestMapping("/{username}/savepublicholiday")
	public String SavePublicHoliday(@PathVariable("username") String username, @ModelAttribute("publicholiday") @Valid PublicHoliday publicholiday, BindingResult bindingResult) {
		System.out.println(publicholiday.getPhName());
		System.out.println(publicholiday.getPhDate());
		phservice.createPublicHoliday(publicholiday);
		return "redirect:/admin/" + username;
	}
	
	@GetMapping("/{username}/leavetypesummary")
	public String LeaveTypeSummary(Model model,@PathVariable("username") String username) {
		System.out.println("------------");
		model.addAttribute("leavetypes", ltservice.findAll());
		System.out.println("This is inside /manager/{username}/leavetypesummary mapping ");
		return "leavetypesummary";
	}
	
	@RequestMapping("/{username}/deletepublicholiday/{id}")
	public String DeletePublicHoliday(@PathVariable("username") String username,@PathVariable("id") Integer id, Model model) {
		PublicHoliday publicHoliday = phservice.findById(id);
		System.out.println("This is inside delete publicholiday");
		phservice.deletePublicHoliday(publicHoliday);
		model.addAttribute("publicholiday",publicHoliday);
		return "redirect:/admin/" + username + "/publicholidaysummary";
	}


	@GetMapping("/{username}/leavetypesummary/addleavetype")
	public String AddLeaveTypey(Model model,@PathVariable("username") String username) {
		System.out.println("------------");
		System.out.println("This is inside /manager/{username}/leavetypesummary mapping ");
		return "viewemployee";
	}	

	@GetMapping("/{username}/leavetypesummary/deleteleavetype")
	public String DeleteLeaveType(Model model,@PathVariable("username") String username) {
		System.out.println("------------");
		System.out.println("This is inside /manager/{username}/leavetypesummary mapping ");
		return "viewemployee";
	}
	
	
	@GetMapping("/{username}/rolesummary")
	public String RoleSummary(Model model,@PathVariable("username") String username) {
		System.out.println("Role Summary");
		model.addAttribute("roles",	rservice.findAll());
		return "rolesummary";
	}
	
	@GetMapping("/{username}/addrole")
	public String AddRole(Model model, @PathVariable("username") String username, HttpSession session) {
		System.out.println("Add Role");
		model.addAttribute("role", new Role());
		return "roledetails";
	}
	
	@GetMapping("/{username}/editrole/{roleid}")
	public String EditRole(@PathVariable("roleid") Integer id, @ModelAttribute("role") Role role, Model model, HttpSession session) {
		System.out.println("Edit Role");
		model.addAttribute("role", rservice.findRoleById(id));
		System.out.println("Object = " + rservice.findRoleById(id));
		return "roledetails";
	}
	
	@RequestMapping(value = "/{username}/role/save", path = "/{username}/role/save", method = { RequestMethod.GET, RequestMethod.POST }, produces = "text/html")
	//@RequestMapping(value = "/{username}/role/save", method = RequestMethod.POST)
	public String saveRole(@PathVariable("username") String username, @ModelAttribute("role") @Valid Role role, 
			BindingResult bindingResult,  Model model, HttpSession session) {
		System.out.println("Save Role");
		System.out.println(role);
		System.out.println("Username - "+session.getAttribute("username"));
		if (bindingResult.hasErrors()) {
			return "roledetails";
		}
		rservice.saveRole(role);
		return "redirect:/admin/"+ session.getAttribute("username") +"/rolesummary";
	}
	
	@GetMapping("/{username}/deleterole/{id}")
	public String DeleteRole(Model model, @PathVariable("username") String username, @PathVariable("id") Integer id, HttpSession session) {
		rservice.deleteRole(rservice.findRoleById(id));
		return "redirect:/admin/" + username +"/rolesummary";
	}
	
	@GetMapping("/{username}/deleteuser/{id}")
	public String DeleteUser(Model model, @PathVariable("username") String username, @PathVariable("id") Integer id, HttpSession session) {
		
		User user = uservice.findUserById(id);
		uservice.deleteUser(user);
		return "redirect:/admin/" + username +"/summary";
	}
	
	
//	@GetMapping("{/{username}/deleterole/{id}")
//	public String DeleteLeaveType(Model model, @PathVariable("username") String username,@PathVariable("id") Integer id, HttpSession session) {
//		ltservice.deleteLeaveType(ltservice.findLeaveTypeByNameandRoleId(leaveTypeName, roleId));
//		return ;
//	}
	
	
}

