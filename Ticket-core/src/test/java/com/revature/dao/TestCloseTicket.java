package com.revature.dao;

public class TestCloseTicket {

	public static void main(String[] args) {
		CloseTicketDAO close = new CloseTicketDAO();
		System.out.println(close.closeTicket("chitra@gmail", "12345"));
	}

}
