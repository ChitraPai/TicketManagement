package com.revature.dao;

public class TestTicketAssignment {
	public static void main(String[] args) {
		TicketAssignmentDAO ticketAssignment=new TicketAssignmentDAO();
//System.out.println(ticketAssignment.ticketAssignment(1));
//ticketAssignment.viewAssignedTickets(3);
//ticketAssignment.resolveTicket(1, "your request has been granted");
//		ticketAssignment.ticketAssignment(2);
//		ticketAssignment.ticketReassignment(2, 4);
//		ticketAssignment.resolveTicket(2, "we have sent the salary slip via mail");
//		ticketAssignment.viewAssignedTickets(4);
		ticketAssignment.deleteTicket(2, 1);
	}
}