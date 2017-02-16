package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.exception.ServiceException;
import com.revature.model.Employee;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	TicketService ticketService = new TicketService();
	User user = new User();
	Employee employee=new Employee();


	@GetMapping("/creation")
	public String creation()
	{
		return "../TicketCreation.jsp";
			
	}
	@GetMapping("/createtickets")
	public String ticketCreation(@RequestParam("subject") String subject, @RequestParam("description") String description,
			@RequestParam("departmentName") String departmentName, @RequestParam("priority") String priority,HttpSession session)
			throws ServiceException {
		user=(User) session.getAttribute("LOGGED_IN_USER");
		ticketService.ticketCreation(user.getEmailId(), subject, description, departmentName, priority);
		return "redirect:/index.jsp";
	}
	
	@GetMapping("/update")
	public String update(HttpSession session,@RequestParam("ticketId") Integer ticketId)
	{
		user=(User) session.getAttribute("LOGGED_IN_USER");
		session.setAttribute("ticketId", ticketId);
		return "../TicketUpdation.jsp";
			
	}		
	
	
	@GetMapping("/updateticket")
	public String ticketUpdation(@RequestParam("description") String description,HttpSession session)
			throws ServiceException {
		int ticketId=(int) session.getAttribute("ticketId");
		ticketService.ticketUpdation(ticketId, description);
		return "redirect:/index.jsp";
	}
	
	
	@GetMapping("/closeticket")
	public String closeTicket(@RequestParam("ticketId") Integer ticketId,HttpSession session)
			throws ServiceException {
		ticketService.closeTicket(ticketId);
		return "redirect:/index.jsp";
	}
	
		
	@GetMapping("/view")
	public String viewTicket(HttpSession session,ModelMap modelMap)
			throws ServiceException {
		user=(User) session.getAttribute("LOGGED_IN_USER");
		List<TicketTransaction> list=ticketService.viewTickets(user.getEmailId());
		modelMap.addAttribute("list",list);
		return "../ViewTickets.jsp";
	}
	@GetMapping("/reassign")
	public String reassign(HttpSession session,@RequestParam("ticketId") Integer ticketId)
	{
		employee=(Employee) session.getAttribute("LOGGED_IN_USER");
		session.setAttribute("ticketId", ticketId);
		return "../TicketReassignment.jsp";
	}
	@GetMapping("/reassigntickets")
	public String reassignTicket(HttpSession session,@RequestParam("employeeId")Integer employeeId)
			throws ServiceException {
		int ticketId=(int) session.getAttribute("ticketId");
		ticketService.ticketReassignment(ticketId, employeeId);
		return "../index.jsp";
	}
	
	@GetMapping("/resolve")
	public String resolve(HttpSession session,@RequestParam("ticketId") Integer ticketId)
	{
		employee=(Employee) session.getAttribute("LOGGED_IN_USER");
		session.setAttribute("ticketId", ticketId);
		return "../ResolveTicket.jsp";
	}
	
	@GetMapping("/resolveticket")
	public String resolveTicket(HttpSession session,@RequestParam("solution")String solution)
			throws ServiceException {
		int ticketId=(int) session.getAttribute("ticketId");
		ticketService.resolveTicket(ticketId, solution);
		return "../index.jsp";
	}
	
	@GetMapping
	public String viewAssignedTickets(HttpSession session,ModelMap modelMap)
			throws ServiceException {
		employee= (Employee) session.getAttribute("LOGGED_IN_USER");
		List<TicketTransaction> list=ticketService.viewAssignedTickets(employee.getEmailId());
		modelMap.addAttribute("list",list);
		return "/ViewAssignedTickets.jsp";
	}
	
	@GetMapping("/delete")
	public String delete()
	{
		return "../DeleteTicket.jsp";
	}
	
	@GetMapping("/deletetickets")
	public String deleteTicket(HttpSession session,@RequestParam("ticketId") Integer ticketId)
			throws ServiceException {
		employee=(Employee) session.getAttribute("LOGGED_IN_USER");
			ticketService.deleteTicket(ticketId, employee.getEmailId());
		return "../index.jsp";
	}
}
