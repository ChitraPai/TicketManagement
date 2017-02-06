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

	public void ticketCreation(String emailId, String password, String subject, String description,
			String departmentName, String priorityName) throws ServiceException {
		if (userService.loginForUser(emailId, password)) {
			try {
				UserValidator.validateForTicketCreation(subject, description, departmentName, priorityName);
				ticketCreation.ticketCreation(emailId, password, subject, description, departmentName, priorityName);
			} catch (ValidationException | DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void ticketUpdation(String emailId, String password, int ticketId, String description)
			throws ServiceException {
		if (userService.loginForUser(emailId, password)) {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateForTicketUpdation(description);
				ticketCreation.ticketUpdation(emailId, password, ticketId, description);
			} catch (ValidationException | DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void closeTicket(String emailId, String password, int ticketId) throws ServiceException {
		if (userService.loginForUser(emailId, password)) {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				ticketCreation.closeTicket(emailId, password, ticketId);
			} catch (ValidationException | DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
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

	public void resolveTicket(String emailId, String password, Integer ticketId, String solution)
			throws ServiceException {
		if (userService.loginForEmployee(emailId, password)) {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateForResolvingTicket(solution);
				ticketAssignment.resolveTicket(ticketId, solution);
			} catch (DataAccessException | ValidationException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
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
