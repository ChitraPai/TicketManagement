package com.revature.dao;

public class TestTicketCreationDAO {

	public static void main(String[] args) {
		TicketCreationDAO ticDAO = new TicketCreationDAO();
		System.out.println(
				ticDAO.ticketCreation("chitra@gmail", "12345", "laptop request", "new laptop requested", "Finance"));
	}

}
