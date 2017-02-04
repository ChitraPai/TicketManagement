package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Priority;

public class TestPriorityDAO {

	public static void main(String[] args) throws PersistenceException {
   Priority priority=new Priority();
   PriorityDAO priorityDAO=new PriorityDAO();
   priority.setName("Medium");
   priorityDAO.save(priority);
   priority.setId(1);
	priorityDAO.update(priority);
	List<Priority> listAll = priorityDAO.listAll();
	for (Priority p : listAll) {
		System.out.println(p);
	}

	}

}
