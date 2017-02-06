package com.revature.dao;

import com.revature.exception.PersistenceException;
import com.revature.model.User;

public class LoginDAO {
	User user = new User();
	UserDAO userDAO = new UserDAO();
	EmployeeDAO employeeDAO = new EmployeeDAO();

	public boolean loginForUser(String emailId, String password) throws PersistenceException {
		userDAO.retrieveUserId(emailId, password);
			return true;
			}
	
	public boolean loginForEmployee(String emailId, String password) throws PersistenceException{
		employeeDAO.retrieveEmployeeId(emailId, password);
			return true;
		
	}
	}

