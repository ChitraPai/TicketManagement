package com.revature.model;

import lombok.Data;

@Data
public class IssueSolution {
	private Integer id;
	private TicketTransaction ticketId;
	private String solution;
}
