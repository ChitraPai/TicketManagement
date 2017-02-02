package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;

public class TestDepartmentDAO {

	public static void main(String[] args) throws PersistenceException {
		Department department = new Department();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		department.setName("Finance");
		departmentDAO.save(department);
		department.setName("HR Department");
		department.setId(1);
		departmentDAO.update(department);
		List<Department> listAll = departmentDAO.listAll();
		for (Department d : listAll) {
			System.out.println(d);
		}

	}

}
