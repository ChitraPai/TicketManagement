package com.revature.service;

import org.springframework.dao.DataAccessException;

import com.revature.dao.TicketCreationDAO;

import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.validator.UserValidator;

public class TicketService {
	private UserService userService = new UserService();
	private TicketCreationDAO ticketCreation = new TicketCreationDAO();

	public void ticketCreation(String emailId, String password, String subject, String description,
			String departmentName, String priorityName) throws ServiceException {
		if (userService.login(emailId, password)) {
			try {
				UserValidator.validateForTicketCreation(subject, description, departmentName, priorityName);
				ticketCreation.ticketCreation(emailId, password, subject, description, departmentName, priorityName);
			} catch (ValidationException e) {
				throw new ServiceException(" ", e);
			} catch (DataAccessException e) {
				throw new ServiceException(" ", e);
			} catch (PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void ticketUpdation(String emailId, String password, int ticketId, String description)
			throws ServiceException {
		if (userService.login(emailId, password)) {
			try {
				UserValidator.validateForTicketUpdation(ticketId, description);
				ticketCreation.ticketUpdation(emailId, password, ticketId, description);
			} catch (ValidationException e) {
				throw new ServiceException(" ", e);
			} catch (DataAccessException e) {
				throw new ServiceException(" ", e);
			} catch (PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	}

	public void closeTicket(String emailId, String password, int ticketId) throws ServiceException {
		if (userService.login(emailId, password)) {
			try {
				UserValidator.validateForCloseTicket(ticketId);
				ticketCreation.closeTicket(emailId, password, ticketId);
			} catch (ValidationException e) {
				throw new ServiceException(" ", e);
			} catch (DataAccessException e) {
				throw new ServiceException(" ", e);
			} catch (PersistenceException e) {
				throw new ServiceException(" ", e);

			}

		}
	}

	public void viewTickets(String emailId, String password) throws ServiceException {
		if (userService.login(emailId, password)) {
			try {
				ticketCreation.viewTickets(emailId, password);
			} catch (DataAccessException e) {
				throw new ServiceException(" ", e);
			} catch (PersistenceException e) {
				throw new ServiceException(" ", e);

			}
		}

	}
}
