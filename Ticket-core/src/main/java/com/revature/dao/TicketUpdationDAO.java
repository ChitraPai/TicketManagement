package com.revature.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.model.Department;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TicketUpdationDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	User user = new User();
	UserDAO userDAO = new UserDAO();
	Department department = new Department();
	DepartmentDAO departmentDAO = new DepartmentDAO();
	LoginDAO loginDAO = new LoginDAO();
	TicketTransaction ticket = new TicketTransaction();
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();

	public boolean ticketUpdation(String emailId, String password, String description) {
		if (loginDAO.login(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId).getId();
			if (ticketDAO.isRequestIdOpen(id)) {
				String sql = "update ticket_transactions set description=?, status='open' where user_id=?";
				Object[] params = { description, id };
				jdbcTemplate.update(sql, params);
			}
			return true;
		}
		return false;
	}
}