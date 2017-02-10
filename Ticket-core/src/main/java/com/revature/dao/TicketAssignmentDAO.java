package com.revature.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.EmailException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.TicketTransaction;
import com.revature.util.ConnectionUtil;
import com.revature.util.MailUtil;

public class TicketAssignmentDAO {
	UserDAO userDAO = new UserDAO();
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();
	LoginDAO loginDAO = new LoginDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(TicketAssignmentDAO.class.getName());

	public void ticketReassignment(String emailId,Integer ticketId, Integer employeeId)
			throws PersistenceException {
		try {
				int id = employeeDAO.getEmployeeIdForEmail(emailId);
				if ((id == ticketDAO.listByTicketId(ticketId).getId())
						&& (ticketDAO.getStatusForId(ticketId) != "close")) {
					String sql = "update ticket_transactions set assigned_employee_id=? where id=?";
					Object[] params = { employeeId, ticketId };
					jdbcTemplate.update(sql, params);
				}
				} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("", e);
		}

	}

	public void viewAssignedTickets(Integer employeeId) throws PersistenceException {
		try {
			List<TicketTransaction> list = ticketDAO.listByAssignedEmployeeId(employeeId);
			Iterator<TicketTransaction> i = list.iterator();
			while (i.hasNext()) {
				TicketTransaction tic = (TicketTransaction) i.next();
				logger.log(Level.INFO, tic.getId() + "\t" + tic.getCreatedDate().toLocalDate() + "\t" + tic.getSubject()
						+ "\t" + tic.getDescription() + "\t" + tic.getStatus());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given employee id doesnt exist", e);
		}

	}

	public boolean replyToTicket(Integer ticketId, String solution) throws PersistenceException {
		try {
			if (ticketDAO.getStatusForId(ticketId) != "close") {
				String sql = "insert into issue_solutions(ticket_id,solution) values(?,?)";
				Object[] params = { ticketId, solution };
				jdbcTemplate.update(sql, params);
				return true;
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("", e);
		}
		return false;
	}

	public void resolveTicket(Integer ticketId, String solution) throws PersistenceException {
		try {
			if (replyToTicket(ticketId, solution)) {
				String sql = "update ticket_transactions set resolved_date=now(),status='resolved' where id=?";
				Object[] params = { ticketId };
				jdbcTemplate.update(sql, params);
				int userId=ticketDAO.listByTicketId(ticketId).getUserId().getId();
				String emailId=userDAO.getEmailForUserId(userId);
				try {
					MailUtil.sendSimpleMail(emailId,"The solution is: " +solution+ " for the ticket id: ",ticketId);
				} catch (EmailException e) {
				}
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("", e);
		}
	}

	public void deleteTicket(Integer ticketId, Integer employeeId) throws PersistenceException {
		try {
			if ("Admin".equals(employeeDAO.retreiveRoleNameforEmployee(employeeId))) {
				String sql = "update ticket_transactions set active=0 where id=?";
				Object[] params = { ticketId };
				jdbcTemplate.update(sql, params);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given ticket Id does not exist", e);
		}
	}
}
