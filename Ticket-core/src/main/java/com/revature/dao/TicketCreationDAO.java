package com.revature.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.TicketTransaction;
import com.revature.util.ConnectionUtil;

public class TicketCreationDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	UserDAO userDAO = new UserDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	DepartmentDAO departmentDAO = new DepartmentDAO();
	PriorityDAO priorityDAO = new PriorityDAO();
	LoginDAO loginDAO = new LoginDAO();
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();
	Logger logger = Logger.getLogger(TicketCreationDAO.class.getName());

	public boolean ticketCreation(String emailId, String password, String subject, String description,
			String departmentName, String priorityName) throws PersistenceException {
		if (loginDAO.loginForUser(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId,password);
			int deptId = departmentDAO.retrieveDepartmentId(departmentName).getId();
			int priorityId = priorityDAO.retrievePriorityId(priorityName).getId();
			int empId= employeeDAO.retrieveEmployeeIdForDepartment(deptId);
			String status="In Progress";
			String sql = "insert into ticket_transactions(user_id,subject,description,department_id,priority_id,assigned_employee_id,status) values(?,?,?,?,?,?,?)";
			Object[] params = { id, subject, description, deptId, priorityId, empId, status};
			jdbcTemplate.update(sql, params);
			return true;
		}
		return false;
	}

	public boolean ticketUpdation(String emailId, String password, int ticketId, String description)
			throws PersistenceException {
		if (loginDAO.loginForUser(emailId, password)) {
			if (ticketDAO.getStatusForId(ticketId) != "close") {
				String sql = "update ticket_transactions set description=?, status='open' where id=?";
				Object[] params = { description, ticketId };
				jdbcTemplate.update(sql, params);
			}
			return true;
		}
		return false;
	}

	public boolean closeTicket(String emailId, String password, int ticketId) throws PersistenceException {
		if (loginDAO.loginForUser(emailId, password)) {
			if (ticketDAO.getStatusForId(ticketId) != "close") {
				String sql = "update ticket_transactions set status='close' where id=?";
				Object[] params = { ticketId };
				jdbcTemplate.update(sql, params);
			}
			return true;
		}
		return false;
	}

	public boolean viewTickets(String emailId, String password) throws PersistenceException {
		if (loginDAO.loginForUser(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId,password);
			List<TicketTransaction> list = ticketDAO.listByUserId(id);
			Iterator<TicketTransaction> i = list.iterator();
			while (i.hasNext()) {
				TicketTransaction tic = (TicketTransaction) i.next();
				logger.log(Level.INFO, tic.getId() + "\t" + tic.getCreatedDate().toLocalDate() + "\t" + tic.getSubject()
						+ "\t\t" + tic.getDescription() + "\t" + tic.getStatus());
			}
			return true;
		}
		return false;
	}
}
