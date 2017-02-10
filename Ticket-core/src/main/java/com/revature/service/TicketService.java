package com.revature.service;

import org.springframework.dao.DataAccessException;

import com.revature.dao.TicketAssignmentDAO;
import com.revature.dao.TicketCreationDAO;

import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.validator.UserValidator;

public class TicketService {
	private UserService userService = new UserService();
	private TicketCreationDAO ticketCreation = new TicketCreationDAO();
	private TicketAssignmentDAO ticketAssignment = new TicketAssignmentDAO();

	public void ticketCreation(String emailId, String subject, String description, String departmentName,
			String priorityName) throws ServiceException {
		try {
			UserValidator.validateForTicketCreation(subject, description, departmentName, priorityName);
			System.out.println("Service Validation done "+emailId);
			boolean result=ticketCreation.ticketCreation(emailId, subject, description, departmentName, priorityName);
			System.out.println("Service Done "+result);
		} catch (ValidationException | DataAccessException | PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

	public void ticketUpdation(String emailId, int ticketId, String description)
			throws ServiceException {
					try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateForTicketUpdation(description);
				ticketCreation.ticketUpdation(emailId, ticketId, description);
			} catch (ValidationException | DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	

	public void closeTicket(int ticketId) throws ServiceException {
		
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				ticketCreation.closeTicket(ticketId);
			} catch (ValidationException | DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	

	public void viewTickets(String emailId, String password) throws ServiceException {
		if (userService.loginForUser(emailId, password)) {
			try {
				ticketCreation.viewTickets(emailId, password);
			} catch (DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void ticketReassignment(String emailId, String password, Integer ticketId, Integer employeeId)
			throws ServiceException {
		if (userService.loginForEmployee(emailId, password)) {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateIfNullEmployeeId(employeeId);
				ticketAssignment.ticketReassignment(emailId, password, ticketId, employeeId);
			} catch (DataAccessException | ValidationException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void resolveTicket(Integer ticketId, String solution) throws ServiceException {
		try {
			UserValidator.validateIfNullTicketId(ticketId);
			UserValidator.validateForResolvingTicket(solution);
			ticketAssignment.resolveTicket(ticketId, solution);
		} catch (DataAccessException | ValidationException | PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

	public void deleteTicket(String emailId, String password, Integer ticketId, Integer employeeId)
			throws ServiceException {
		if (userService.loginForEmployee(emailId, password)) {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateIfNullEmployeeId(employeeId);
				ticketAssignment.deleteTicket(ticketId, employeeId);
			} catch (DataAccessException | ValidationException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

}
