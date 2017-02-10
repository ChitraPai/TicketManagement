package com.revature.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.EmailException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.TicketTransaction;
import com.revature.util.ConnectionUtil;
import com.revature.util.MailUtil;

public class TicketCreationDAO {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	UserDAO userDAO = new UserDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();
	DepartmentDAO departmentDAO = new DepartmentDAO();
	PriorityDAO priorityDAO = new PriorityDAO();
	LoginDAO loginDAO = new LoginDAO();
	TicketTransactionDAO ticketDAO = new TicketTransactionDAO();
	Logger logger = Logger.getLogger(TicketCreationDAO.class.getName());

	public boolean ticketCreation(String emailId, String subject, String description, String departmentName,
			String priorityName) throws PersistenceException {

		int id = userDAO.getUserIdForEmail(emailId);
		int deptId = departmentDAO.retrieveDepartmentId(departmentName).getId();
		int priorityId = priorityDAO.retrievePriorityId(priorityName).getId();
		int empId = employeeDAO.listByDepartmentId(deptId).getId();
		System.out.println("Emp Id " + empId);
		String empMail = employeeDAO.listByDepartmentId(deptId).getEmailId();
		System.out.println("Mail Id " + empMail);
		String status = "In Progress";

		String sql = "insert into ticket_transactions(user_id,subject,description,department_id,priority_id,assigned_employee_id,status) values(?,?,?,?,?,?,?)";
		Object[] params = { id, subject, description, deptId, priorityId, empId, status };
		jdbcTemplate.update(sql, params);
		System.out.println(id + " " + deptId + " " + priorityId + " " + empId + " " + empMail);
		int ticketId = ticketDAO.retrieveTicketId(id, subject, description, deptId, priorityId);
		try {
			MailUtil.sendSimpleMail(emailId, "Ticket Created Sucessfully.Your Ticket id is:", ticketId);
			System.out.println("Mail done 1");
			MailUtil.sendSimpleMail(empMail, "A ticket has been created. The issue id is:", ticketId);
			System.out.println("Mail done 2");
		} catch (EmailException e) {
			throw new PersistenceException("Mail Not send", e);
		}
		return true;

		// return false;
	}

	public boolean ticketUpdation(String emailId, int ticketId, String description) throws PersistenceException {
		if (ticketDAO.getStatusForId(ticketId) != "close") {
			String sql = "update ticket_transactions set description=?, status='In progress' where id=?";
			Object[] params = { description, ticketId };
			jdbcTemplate.update(sql, params);
		}
		return true;
	}

	public boolean closeTicket(int ticketId) throws PersistenceException {
		if (ticketDAO.getStatusForId(ticketId) != "close") {
			String sql = "update ticket_transactions set status='close' where id=?";
			Object[] params = { ticketId };
			jdbcTemplate.update(sql, params);
		}
		return true;
	}

	public boolean viewTickets(String emailId, String password) throws PersistenceException {
		if (loginDAO.loginForUser(emailId, password)) {
			int id = userDAO.retrieveUserId(emailId, password);
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
