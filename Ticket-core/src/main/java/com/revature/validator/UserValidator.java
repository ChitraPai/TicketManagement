package com.revature.validator;

import com.revature.exception.ValidationException;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.util.ValidationUtil;

public class UserValidator {
	private UserValidator() {
	}

	public static void validateIfNullEmailIdPassword(String emailId, String password) throws ValidationException {
		ValidationUtil.rejectIfNullOrEmpty(emailId, " Email Id");
		ValidationUtil.rejectIfNullOrEmpty(password, " Password");
	}

	public static void validateForUserRegistration(User user) throws ValidationException {

		ValidationUtil.rejectIfNullOrEmpty(user.getName(), " Name");
		ValidationUtil.rejectIfNullOrEmpty(user.getEmailId(), " EmailId");
		ValidationUtil.rejectIfNullOrEmpty(user.getPassword(), " Password");

	}

	public static void validateForEmployeeRegistration(Employee employee) throws ValidationException {
		ValidationUtil.rejectIfNullOrEmpty(employee.getName(), " Name");
		ValidationUtil.rejectIfNullOrEmpty(employee.getEmailId(), " EmailId");
		ValidationUtil.rejectIfNullOrEmpty(employee.getPassword(), " Password");
		ValidationUtil.rejectIfNullOrNegative(employee.getRoleId().getId(), " Role Id");
		ValidationUtil.rejectIfNullOrNegative(employee.getDepartmentId().getId(), " Department id");
	}

	public static void validateForTicketCreation(String subject, String description, String departmentName,
			String priorityName) throws ValidationException {
		ValidationUtil.rejectIfNullOrEmpty(subject, " Subject");
		ValidationUtil.rejectIfNullOrEmpty(description, " Description");
		ValidationUtil.rejectIfNullOrEmpty(departmentName, " Department Name");
		ValidationUtil.rejectIfNullOrEmpty(priorityName, " Priority");

	}

	public static void validateIfNullTicketId(Integer ticketId) throws ValidationException {
		ValidationUtil.rejectIfNullOrNegative(ticketId, " Ticket Id");
	}

	public static void validateForTicketUpdation(String description) throws ValidationException {
		ValidationUtil.rejectIfNullOrEmpty(description, " Description");
	}

	public static void validateIfNullEmployeeId(Integer employeeId) throws ValidationException {
		ValidationUtil.rejectIfNullOrNegative(employeeId, " Employee Id");
	}

	public static void validateForResolvingTicket(String solution) throws ValidationException {
		ValidationUtil.rejectIfNullOrEmpty(solution, " Solution");
	}

}
