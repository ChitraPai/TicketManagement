package com.revature.dao;

import com.revature.exception.PersistenceException;
import com.revature.model.User;

public class LoginDAO {
	User user = new User();
	UserDAO userDAO = new UserDAO();

	public boolean login(String emailId, String password) throws PersistenceException {

		if (userDAO.retrievePassword(emailId).equals(password)) {
			return true;
		}

		return false;
	}
}
