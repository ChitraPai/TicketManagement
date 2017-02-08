package com.revature.dao;


import org.apache.commons.mail.EmailException;
import org.springframework.dao.DataAccessException;

import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.util.MailUtil;
import com.revature.validator.UserValidator;

public class TestTicketCreationDAO {

	public static void main(String[] args) throws  PersistenceException {
		TicketCreationDAO ticDAO = new TicketCreationDAO();
//		System.out.println(
//				ticDAO.ticketUpdation("aiswarya@gmail", "123abc", 2,"salry slip is requested for submission"));
		
//ticDAO.viewTickets("aiswarya@gmail", "123abc");
//		ticDAO.ticketUpdation("aiswarya@gmail", "123abc", 2,"salary slip is required for submission");
//		ticDAO.closeTicket("aiswarya@gmail", "123abc", 2);
//		System.out.println(ticDAO.ticketCreation("rashik@gmail", "aabbcc","Hike in salary","Hike in salary is requested", "HR department","high"));
//	ticDAO.ticketCreation("chitrapaij@gmail.com", "12345", "Laptop is not working", "laptop is not working and shutting down frequently", "Finance", "High");
		
//		public boolean ticketCreation(String emailId, String password, String subject, String description,
//				String departmentName, String priorityName) throws PersistenceException {
//			if (loginDAO.loginForUser(emailId, password)) {
//				int id = userDAO.retrieveUserId(emailId,password);
//				int deptId = departmentDAO.retrieveDepartmentId(departmentName).getId();
//				int priorityId = priorityDAO.retrievePriorityId(priorityName).getId();
//				int empId= employeeDAO.listByDepartmentId(deptId).getId();
//				String empMail=employeeDAO.listByDepartmentId(deptId).getEmailId();
//				String status="In Progress";
//				String sql = "insert into ticket_transactions(user_id,subject,description,department_id,priority_id,assigned_employee_id,status) values(?,?,?,?,?,?,?)";
//				Object[] params = { id, subject, description, deptId, priorityId, empId, status};
//				jdbcTemplate.update(sql, params);
//				int ticketId=ticketDAO.retrieveTicketId(id, subject, description, deptId, priorityId);
//				try {
//					MailUtil.sendSimpleMail(emailId,"Ticket Created Sucessfully.Your Ticket id is:",ticketId);
//					MailUtil.sendSimpleMail(empMail,"A ticket has been created. The issue id is:",ticketId);
//				} catch (EmailException e) {
//				}
//			return true;
//			}
//			return false;
//		}
		
		
//		public void ticketCreation(String emailId, String password, String subject, String description,
//				String departmentName, String priorityName) throws ServiceException {
//			if (userService.loginForUser(emailId, password)) {
//				try {
//					UserValidator.validateForTicketCreation(subject, description, departmentName, priorityName);
//					ticketCreation.ticketCreation(emailId, subject, description, departmentName, priorityName);
//				} catch (ValidationException | DataAccessException | PersistenceException e) {
//					throw new ServiceException(" ", e);
//				}
//			}
		}
	}


