package com.revature.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.revature.dao.TicketAssignmentDAO;
import com.revature.dao.TicketCreationDAO;

import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.model.TicketTransaction;
import com.revature.validator.UserValidator;

public class TicketService {
	private UserService userService = new UserService();
	private TicketCreationDAO ticketCreation = new TicketCreationDAO();
	private TicketAssignmentDAO ticketAssignment = new TicketAssignmentDAO();

	public void ticketCreation(String emailId, String subject, String description, String departmentName,
			String priorityName) throws ServiceException {
		try {
			UserValidator.validateForTicketCreation(subject, description, departmentName, priorityName);
			ticketCreation.ticketCreation(emailId, subject, description, departmentName, priorityName);
				} catch (ValidationException | DataAccessException | PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

	public void ticketUpdation(int ticketId, String description)
			throws ServiceException {
					try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateForTicketUpdation(description);
				ticketCreation.ticketUpdation(ticketId, description);
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
	

	public List<TicketTransaction> viewTickets(String emailId) throws ServiceException {
				try {
				return ticketCreation.viewTickets(emailId);
			} catch (DataAccessException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	

	public void ticketReassignment(Integer ticketId, Integer employeeId)
			throws ServiceException {
	
			try {
				UserValidator.validateIfNullTicketId(ticketId);
				UserValidator.validateIfNullEmployeeId(employeeId);
				ticketAssignment.ticketReassignment(ticketId, employeeId);
			} catch (DataAccessException | ValidationException | PersistenceException e) {
				throw new ServiceException(" ", e);
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

	public void deleteTicket(Integer ticketId, String emailId)
			throws ServiceException {
			try {
				UserValidator.validateIfNullTicketId(ticketId);
			
				ticketAssignment.deleteTicket(ticketId, emailId);
			} catch (DataAccessException | ValidationException | PersistenceException e) {
				throw new ServiceException(" ", e);
			}
		}
	
	public List<TicketTransaction> viewAssignedTickets(String emailId) throws ServiceException {
		try {
		return ticketAssignment.viewAssignedTickets(emailId);
	} catch (DataAccessException | PersistenceException e) {
		throw new ServiceException(" ", e);
	}
}
	
	}
