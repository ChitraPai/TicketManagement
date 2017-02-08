package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	TicketService ticketService = new TicketService();

	@GetMapping("/creation")
	public String ticketCreation(@RequestParam("emailId") String emailId,ModelMap modelMap)
	{
		modelMap.addAttribute("emailId", emailId);
		return "/ticketcreation.jsp";
			
	}
	@GetMapping("/createtickets")
	public String ticketCreation(@RequestParam("emailId") String emailId,@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("departmentName") String departmentName, @RequestParam("priorityName") String priorityName)
			throws ServiceException {
		ticketService.ticketCreation(emailId, subject, description, departmentName, priorityName);
		return "redirect:/thankyou.jsp";
	}
}
