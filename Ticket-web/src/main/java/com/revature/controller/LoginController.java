package com.revature.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.User;
import com.revature.service.UserService;

@Controller
@RequestMapping("/home")
public class LoginController {
	User user = new User();
	UserService userService = new UserService();

	@GetMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password, ModelMap modelMap) throws ServiceException {

		user.setName(name);
		user.setEmailId(emailId);
		user.setPassword(password);
		try {
			userService.registerForUser(user);
			return "redirect:/userticketoptions.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "/register.jsp";
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
			return "../userticketoptions.jsp";
		} catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
			return "/userlogin.jsp";
		}

	}

	@PostMapping("/employeelogin")
	public String employeeLogin(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap) throws ServiceException {
		try {
		userService.loginForEmployee(emailId, password);
		return "redirect:/employeeticketoptions.jsp";
		}
		catch (ServiceException e) {
			modelMap.addAttribute("ERROR", e.getMessage());
		return "redirect:/employeelogin.jsp";
	}
}
}