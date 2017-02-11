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

	public void ticketReassignment(String emailId, Integer ticketId, Integer employeeId) throws PersistenceException {
		try {
			int id = employeeDAO.getEmployeeIdForEmail(emailId);
			if ((id == ticketDAO.listByTicketId(ticketId).getAssignedEmployeeId().getId())
					&& (ticketDAO.listByTicketId(ticketId).getStatus() != "close")) {
				String sql = "update ticket_transactions set assigned_employee_id=? where id=?";
				Object[] params = { employeeId, ticketId };
				jdbcTemplate.update(sql, params);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("", e);
		}

	}

	public List<TicketTransaction> viewAssignedTickets(String emailId) throws PersistenceException {
		try {
			int employeeId = employeeDAO.getEmployeeIdForEmail(emailId);
			return ticketDAO.listByAssignedEmployeeId(employeeId);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given email id doesnt exist", e);
		}
	}

	public boolean replyToTicket(String emailId, Integer ticketId, String solution) throws PersistenceException {
		try {
			if (ticketDAO.listByTicketId(ticketId).getAssignedEmployeeId().getId() == employeeDAO
					.getEmployeeIdForEmail(emailId)) {
				if (ticketDAO.listByTicketId(ticketId).getStatus() != "close") {
					String sql = "insert into issue_solutions(ticket_id,solution) values(?,?)";
					Object[] params = { ticketId, solution };
					jdbcTemplate.update(sql, params);
					return true;
				}
			}
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("", e);
		}
		return false;
	}

	public void resolveTicket(String emailId, Integer ticketId, String solution) throws PersistenceException {
		try {
			if (replyToTicket(emailId, ticketId, solution)) {
				String sql = "update ticket_transactions set resolved_date=now(),status='resolved' where id=?";
				Object[] params = { ticketId };
				jdbcTemplate.update(sql, params);
				int userId = ticketDAO.listByTicketId(ticketId).getUserId().getId();
				String userEmailId = userDAO.getEmailForUserId(userId);
				try {
					MailUtil.sendSimpleMail(userEmailId, "The solution is: " + solution + "." + " The ticket id: ",
							ticketId);
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
