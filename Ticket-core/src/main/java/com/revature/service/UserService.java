package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.LoginDAO;
import com.revature.dao.UserDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.validator.UserValidator;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	private LoginDAO loginDAO = new LoginDAO();
	

	public void registerForUser(User user) throws ServiceException {
		try {
			UserValidator.validateForUserRegistration(user);
			userDAO.save(user);
		} catch (ValidationException e) {
			throw new ServiceException("Unable to register", e);
		} catch (PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}
	
	public void registerForEmployee(Employee employee) throws ServiceException {
		try {
			UserValidator.validateForEmployeeRegistration(employee);
			employeeDAO.save(employee);

		} catch (ValidationException e) {
			throw new ServiceException("Unable to register", e);
		} catch (PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

	public boolean loginForUser(String emailId, String password) throws ServiceException {
		try {
			UserValidator.validateIfNullEmailIdPassword(emailId, password);
			loginDAO.loginForUser(emailId, password);
			return true;
		} catch (ValidationException e) {
			throw new ServiceException("Unable to login", e);
		} catch (PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}
	
	public boolean loginForEmployee(String emailId, String password) throws ServiceException {
		try {
		UserValidator.validateIfNullEmailIdPassword(emailId, password);
		loginDAO.loginForEmployee(emailId, password);
		return true;
	} catch (ValidationException e) {
		throw new ServiceException("Unable to login", e);
	} catch (PersistenceException e) {
		throw new ServiceException(" ", e);
	}
}
	

}
