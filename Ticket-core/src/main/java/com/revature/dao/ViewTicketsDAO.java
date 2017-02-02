package com.revature.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.model.Department;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ViewTicketsDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(ViewTicketsDAO.class.getName());
	User user = new User();
	UserDAO userDAO = new UserDAO();
	Department department = new Department();
	DepartmentDAO departmentDAO = new DepartmentDAO();
	LoginDAO loginDAO = new LoginDAO();
	TicketTransaction ticket = new TicketTransaction();
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();

	public boolean viewTickets(String emailId, String password) {
		if (loginDAO.login(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId).getId();
			ticket = ticketDAO.listByUserId(id);
			logger.log(Level.INFO, ticket.getSubject() + "\t" + ticket.getDescription() + "\t" + ticket.getStatus());
			return true;
		}
		return false;
	}
}
