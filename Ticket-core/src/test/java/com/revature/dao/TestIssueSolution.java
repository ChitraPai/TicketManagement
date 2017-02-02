package com.revature.dao;

import java.util.Iterator;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.IssueSolution;

public class TestIssueSolution {

	public static void main(String[] args) {
		IssueSolution issue = new IssueSolution();
		IssueSolutionDAO issueDAO = new IssueSolutionDAO();
		List<IssueSolution> list = issueDAO.listAll();
		Iterator<IssueSolution> i = list.iterator();
		while (i.hasNext()) {
			IssueSolution sol = (IssueSolution) i.next();
			System.out.println(sol.getId() + "\t" + sol.getTicketId().getId() + "\t" + sol.getSolution());

		}

	}

}
