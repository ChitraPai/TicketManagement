package com.revature.dao;

import com.revature.model.User;

public class LoginDAO {
	User user = new User();
	UserDAO userDAO = new UserDAO();

	public boolean login(String emailId, String password) {
		String s = userDAO.retrievePassword(emailId).getPassword();
		if (s.equals(password)) {
			return true;
		}
		return false;
	}
}
