package com.revature.service;

import com.revature.dao.LoginDAO;
import com.revature.dao.UserDAO;
import com.revature.exception.PersistenceException;
import com.revature.exception.ServiceException;
import com.revature.exception.ValidationException;
import com.revature.model.User;
import com.revature.validator.UserValidator;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	private LoginDAO loginDAO = new LoginDAO();
	private User user = new User();

	public void register(String name, String emailId, String password) throws ServiceException {
		try {
			UserValidator.validateForRegistration(name, emailId, password);
			userDAO.save(user);

		} catch (ValidationException e) {
			throw new ServiceException("Unable to register", e);
		} catch (PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

	public boolean login(String emailId, String password) throws ServiceException {
		try {
			UserValidator.validateForLogin(emailId, password);
			loginDAO.login(emailId, password);
			return true;
		} catch (ValidationException e) {
			throw new ServiceException("Unable to login", e);
		} catch (PersistenceException e) {
			throw new ServiceException(" ", e);
		}
	}

}
