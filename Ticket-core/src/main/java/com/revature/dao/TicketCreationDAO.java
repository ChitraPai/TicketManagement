package com.revature.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.model.Department;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TicketCreationDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	User user = new User();
	UserDAO userDAO = new UserDAO();
	Department department = new Department();
	DepartmentDAO departmentDAO = new DepartmentDAO();
	LoginDAO loginDAO = new LoginDAO();

	public boolean ticketCreation(String emailId, String password, String subject, String description,
			String departmentName) {
		if (loginDAO.login(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId).getId();
			int deptId = departmentDAO.retrieveDepartmentId(departmentName).getId();
			String sql = "insert into ticket_transactions(user_id,subject,description,department_id) values(?,?,?,?)";
			Object[] params = { id, subject, description, deptId };
			jdbcTemplate.update(sql, params);
			return true;
		}
		return false;
	}

}
