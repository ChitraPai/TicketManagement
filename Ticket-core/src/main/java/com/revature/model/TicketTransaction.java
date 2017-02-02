package com.revature.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TicketTransaction {
	private Integer id;
	private User userId;
	private String subject;
	private String description;
	private LocalDateTime createdDate;
	private Department departmentId;
	private Employee assignedEmployeeId;
	private LocalDateTime resolvedDate;
	private String status;
}
