package com.revature.model;

import lombok.Data;

@Data
public class Employee {
	private Integer id;
	private Department departmentId;
	private Role roleId;
	private String name;
	private String emailId;
	private String password;
	private Boolean active;
	
}
