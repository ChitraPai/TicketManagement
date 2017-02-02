package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.User;

public class TestUserDAO {

	public static void main(String[] args) throws PersistenceException {
		User user = new User();
		UserDAO userDAO = new UserDAO();
//		user.setName("Priya");
//	user.setEmailId("priya@gmail");
//		user.setPassword("12abc45");
//		userDAO.save(user);
		
// user.setEmailId("priya@");
//		user.setPassword("aaaa");
//		userDAO.update(user);
		List<User> listAll = userDAO.listAll();
		 for(User u: listAll){
		    	System.out.println(u);
		    }

	}

}
