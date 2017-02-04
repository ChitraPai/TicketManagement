package com.revature.validator;

import com.revature.exception.ValidationException;
import com.revature.util.ValidationUtil;

public class UserValidator {
	public static void validateForRegistration(String name, String emailId, String password)
			throws ValidationException {

		ValidationUtil.rejectIfNullOrEmpty(name, "Name");
		ValidationUtil.rejectIfNullOrEmpty(emailId, "Email Id");
		ValidationUtil.rejectIfNullOrEmpty(password, "Password");

	}
	
	public static void validateForLogin(String emailId, String password) throws ValidationException
	{
		ValidationUtil.rejectIfNullOrEmpty(emailId, "Email Id");
		ValidationUtil.rejectIfNullOrEmpty(password, "Password");	
	}
   
	public static void validateForTicketCreation(String subject,String description,String departmentName,String priorityName) throws ValidationException{
		ValidationUtil.rejectIfNullOrEmpty(subject, "Subject");
		ValidationUtil.rejectIfNullOrEmpty(description, "Description");	
		ValidationUtil.rejectIfNullOrEmpty(departmentName, "Department Name");
		ValidationUtil.rejectIfNullOrEmpty(priorityName, "Priority");	
		
	}
	public static void validateForTicketUpdation(int ticketId,String description) throws ValidationException{
		ValidationUtil.rejectIfNullOrNegative(ticketId, "Ticket Id");	
		ValidationUtil.rejectIfNullOrEmpty(description, "Description");	
	}
	
	public static void validateForCloseTicket(int ticketId) throws ValidationException{
		ValidationUtil.rejectIfNullOrNegative(ticketId, "Ticket Id");
	}
}
