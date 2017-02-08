package com.revature.service;

import com.revature.exception.ServiceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Role;
import com.revature.model.User;

public class TestUserService {

	public static void main(String[] args) throws ServiceException {
		UserService userService = new UserService();
		User user = new User();
		Employee employee = new Employee();
		// userService.register("aiswini","chitra@gmail", "1234567");
		// userService.login("aiswini@gmail", "1234567");
		// TicketService ticketService=new TicketService();
		// ticketService.ticketCreation("", "", "", "", "","");
		// ticketService.ticketUpdation("", "", ,"");
		// ticketService.closeTicket(chitra@gmail, password, ticketId);
		// ticketService.viewTickets("","");

		// user.setName("Sindhu");
		// user.setEmailId("sindhu@gmail.com");
		// user.setPassword("112233");
		// userService.registerForUser(user);

//		Department department = new Department();
//		Role role = new Role();
//		employee.setName("Jack");
//		department.setId(5);
//		employee.setDepartmentId(department);
//		role.setId(2);
//		employee.setRoleId(role);
//		employee.setEmailId("jhonson@gmail.com");
//		employee.setPassword("aabb");
//		userService.registerForEmployee(employee);
//userService.loginForUser("chitrapaij@gmail.com", "12345");
//		userService.loginForEmployee("jhonson@gma.com","aabb");
		
//userService.loginForUser("aacc", "123");
		
		TicketService ticketService=new TicketService();
ticketService.ticketCreation("chitrapaij@gmail.com", "Wifi connectivity","laptop not getting connected", "Finance","Medium");
	}

}
