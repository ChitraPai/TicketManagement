package com.revature.dao;

import com.revature.exception.PersistenceException;

public class TestLoginDAO {

	public static void main(String[] args) throws PersistenceException {
		LoginDAO loginDAO = new LoginDAO();
		System.out.println(loginDAO.loginForUser("chitra@gmail", "123"));
//		System.out.println(loginDAO.loginForEmployee("david@gmail", "aaccbb"));
	}

}
