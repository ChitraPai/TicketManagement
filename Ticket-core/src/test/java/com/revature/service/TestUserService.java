package com.revature.service;

import com.revature.exception.ServiceException;

public class TestUserService {

	public static void main(String[] args) throws ServiceException {
		UserService userService = new UserService();
//		userService.register("aiswini","chitra@gmail", "1234567");
//		userService.login("aiswini@gmail", "1234567");
	 TicketService ticketService=new TicketService();
	 ticketService.ticketCreation("", "", "", "", "","");
//		ticketService.ticketUpdation("", "",  ,"");
//		ticketService.closeTicket(chitra@gmail, password, ticketId);
//	 ticketService.viewTickets("","");
	}

}
