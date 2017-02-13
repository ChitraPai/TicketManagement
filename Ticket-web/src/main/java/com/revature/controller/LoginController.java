package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.service.UserService;

@Controller
@RequestMapping("/home")
public class LoginController {
	User user = new User();
	Employee employee=new Employee();
	UserService userService = new UserService();

	@GetMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password, ModelMap modelMap,HttpSession session) throws ServiceException {
		session.setAttribute("LOGGED_IN_USER", user);
		user.setName(name);
		user.setEmailId(emailId);
		user.setPassword(password);
		try {
			userService.registerForUser(user);
			return "../UserLogin.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "/Register.jsp";
		}
	}

	@GetMapping("/userlogin")
	public String userLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap,HttpSession session) throws ServiceException {
		try {
			user.setEmailId(emailId);
			user.setPassword(password);
			if(userService.loginForUser(emailId, password)){
				session.setAttribute("LOGGED_IN_USER", user);
			}
			modelMap.addAttribute("emailId", emailId);
			return "../UserTicketOptions.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "/UserLogin.jsp";
		}

	}

	@GetMapping("/employeelogin")
	public String employeeLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap,HttpSession session) throws ServiceException {
		try {
			employee.setEmailId(emailId);
			employee.setPassword(password);
			boolean emp = userService.loginForEmployee(emailId, password);
			if(emp){
			session.setAttribute("LOGGED_IN_USER", employee);
			}
			modelMap.addAttribute("emailId", emailId);
			return "../EmployeeTicketOptions.jsp";
		}
		catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
		return "../EmployeeLogin.jsp";
	}
}
}