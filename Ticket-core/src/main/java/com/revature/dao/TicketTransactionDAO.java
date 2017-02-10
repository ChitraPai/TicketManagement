package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Priority;
import com.revature.model.TicketTransaction;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TicketTransactionDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public List<TicketTransaction> listAll() {
		String sql = "select id,user_id,subject,description,priority_id,created_date,department_id,status from ticket_transactions";

		return jdbcTemplate.query(sql, (rs, rowNum) -> convert(rs));

	}

	private TicketTransaction convert(ResultSet rs) throws SQLException {
		TicketTransaction ticket = new TicketTransaction();
		ticket.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		ticket.setUserId(user);
		ticket.setSubject(rs.getString("subject"));
		ticket.setDescription(rs.getString("description"));
		Priority priority = new Priority();
		priority.setId(rs.getInt("priority_id"));
		ticket.setPriorityId(priority);
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
	}

	public String getStatusForId(int ticketId) throws PersistenceException {
		try {
			String sql = "select status from ticket_transactions where id=?";
			Object[] params = { ticketId };
			return jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given Ticket Id does not exist",e);
		}
	}

	public List<TicketTransaction> listByUserId(int id) {
		String sql = "select id,subject,description,created_date,resolved_date,status from ticket_transactions where user_id=?";
		return ticketView(id, sql);
	}

	private List<TicketTransaction> ticketView(int id, String sql) {
		Object[] params = { id };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			TicketTransaction ticket = new TicketTransaction();
			ticket.setId(rs.getInt("id"));
			ticket.setSubject(rs.getString("subject"));
			ticket.setDescription(rs.getString("description"));
			ticket.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
			ticket.setResolvedDate(rs.getTimestamp("resolved_date").toLocalDateTime());
			ticket.setStatus(rs.getString("status"));
			return ticket;
		});
	}

	public List<TicketTransaction> listByAssignedEmployeeId(int id) {
		String sql = "select id,subject,description,created_date,status from ticket_transactions where assigned_employee_id=?";
		return ticketView(id, sql);
	}

	public TicketTransaction listByTicketId(int ticketId) throws PersistenceException {
		try {
			String sql = "select * from ticket_transactions where id=?";
			Object[] params = { ticketId };
			return  jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> convert(rs));
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given Ticket Id does not exist",e);
		}

	}
	
	
	public Integer retrieveTicketId(int userId, String subject, String description,
			Integer departmentId, Integer priorityId) throws PersistenceException{
		try {
		String sql = "select id from ticket_transactions where user_id=? and subject=? and description=? and department_id=? and priority_id=?";
		Object[] params = { userId,subject,description,departmentId,priorityId };		
		return jdbcTemplate.queryForObject(sql,params,Integer.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Invalid credentials",e);
		}

	}
}
