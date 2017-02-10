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
import com.revature.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	TicketService ticketService = new TicketService();
	User user = new User();


	@GetMapping("/creation")
	public String creation()
	{
		return "../ticketcreation.jsp";
			
	}
	@GetMapping("/createtickets")
	public String ticketCreation(@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("departmentName") String departmentName, @RequestParam("priority") String priority,HttpSession session)
			throws ServiceException {
		user=(User) session.getAttribute("LOGGED_IN_USER");
		ticketService.ticketCreation(user.getEmailId(), subject, description, departmentName, priority);
		return "redirect:/index.jsp";
	}
	
	
	@GetMapping("/updation")
	public String Updation(@RequestParam("emailId") String emailId,ModelMap modelMap)
	{
	modelMap.addAttribute("emailId", emailId);
		return "../ticketupdation.jsp";
			
	}
	
	@GetMapping("/ticketupdation")
	public String ticketUpdation(@RequestParam("emailId") String emailId,@RequestParam("ticketId") Integer ticketId,@RequestParam("description") String description)
			throws ServiceException {
		ticketService.ticketUpdation(emailId, ticketId, description);
		return "redirect:/index.jsp";
	}
	
	@GetMapping("/close")
	public String Close(@RequestParam("emailId") String emailId,ModelMap modelMap)
	{
	modelMap.addAttribute("emailId", emailId);
		return "../closeticket.jsp";
			
	}
	
	@GetMapping("/closeTicket")
	public String closeTicket(@RequestParam("emailId") String emailId,@RequestParam("ticketId") Integer ticketId)
			throws ServiceException {
		ticketService.closeTicket(ticketId);
		return "redirect:/index.jsp";
	}
}
