package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Role;

public class TestRoleDAO {

	public static void main(String[] args) throws PersistenceException {
		Role role = new Role();
		RoleDAO roleDAO = new RoleDAO();
//		role.setName("Admin");
//		roleDAO.save(role);
		List<Role> listAll = roleDAO.listAll();
		 for (Role r : listAll) {
		 System.out.println(r);
		 }
		
	}

}
