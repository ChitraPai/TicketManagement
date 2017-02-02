package com.revature.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TicketTransactionDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public List<TicketTransaction> listAll() {
		String sql = "select id,user_id,subject,description,created_date,department_id,assigned_employee_id,resolved_date,status from ticket_transactions";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			TicketTransaction ticket = new TicketTransaction();
			ticket.setId(rs.getInt("id"));
			User user = new User();
			user.setId(rs.getInt("user_id"));
			ticket.setUserId(user);
			ticket.setSubject(rs.getString("subject"));
			ticket.setDescription(rs.getString("description"));
			ticket.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
			Department department = new Department();
			department.setId(rs.getInt("department_id"));
			ticket.setDepartmentId(department);
			Employee employee = new Employee();
			employee.setId(rs.getInt("assigned_employee_id"));
			ticket.setAssignedEmployeeId(employee);
			ticket.setResolvedDate(rs.getTimestamp("resolved_date").toLocalDateTime());
			ticket.setStatus(rs.getString("status"));
			return ticket;

		});

	}

	public boolean isRequestIdOpen(int id) {
		String sql = "select user_id from ticket_transactions where user_id=? and status<>'closed'";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			TicketTransaction ticket = new TicketTransaction();
			ticket.setId(rs.getInt("user_id"));
			return true;
		});

	}

	public TicketTransaction listByUserId(int id) {
		String sql = "select subject,description,status from ticket_transactions where user_id=?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			TicketTransaction ticket = new TicketTransaction();
			ticket.setSubject(rs.getString("subject"));
			ticket.setDescription(rs.getString("description"));
			ticket.setStatus(rs.getString("status"));

			return ticket;
		});
	}
}
