package com.revature.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.model.TicketTransaction;
import com.revature.util.ConnectionUtil;

public class TicketAssignmentDAO {
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = Logger.getLogger(TicketAssignmentDAO.class.getName());

	public boolean ticketAssignment(int ticketId) {

		int deptId = ticketDAO.getDepartmentForTicket(ticketId);
		int employeeId = employeeDAO.retrieveEmployeeIdForDepartment(deptId);
		updateTicket(ticketId, employeeId);
		return true;

	}

	public void ticketReassignment(Integer ticketId, Integer employeeId) {

		updateTicket(ticketId, employeeId);

	}

	private void updateTicket(Integer ticketId, Integer employeeId) {
		if (ticketDAO.getStatusForId(ticketId) != "close") {
			String sql = "update ticket_transactions set assigned_employee_id=? where id=?";
			Object[] params = { employeeId, ticketId };
			jdbcTemplate.update(sql, params);
		}
	}

	public void viewAssignedTickets(Integer employeeId) {
		List<TicketTransaction> list = ticketDAO.listByAssignedEmployeeId(employeeId);
		Iterator<TicketTransaction> i = list.iterator();
		while (i.hasNext()) {
			TicketTransaction tic = (TicketTransaction) i.next();
			logger.log(Level.INFO, tic.getId() + "\t" + tic.getCreatedDate().toLocalDate() + "\t" + tic.getSubject()
					+ "\t" + tic.getDescription() + "\t" + tic.getStatus());
		}

	}

	public boolean replyToTicket(Integer ticketId, String solution) {
		if (ticketDAO.getStatusForId(ticketId) != "close") {
			String sql = "insert into issue_solutions(ticket_id,solution) values(?,?)";
			Object[] params = { ticketId, solution };
			jdbcTemplate.update(sql, params);
			return true;
		}
		return false;
	}

	public void resolveTicket(Integer ticketId, String solution) {
		if (replyToTicket(ticketId, solution)) {
			String sql = "update ticket_transactions set resolved_date=now(),status='resolved' where id=?";
			Object[] params = { ticketId };
			jdbcTemplate.update(sql, params);
		}
	}

	public void deleteTicket(Integer ticketId, Integer employeeId) {
		if ("Admin".equals(employeeDAO.retreiveRoleNameforEmployee(employeeId))) {
			String sql = "update ticket_transactions set active=0 where id=?";
			Object[] params = { ticketId };
			jdbcTemplate.update(sql, params);
		}
	}

}
