package com.revature.dao;

import java.util.Iterator;
import java.util.List;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Role;

public class TestEmployeeDAO {

	public static void main(String[] args) throws PersistenceException {
		Employee employee = new Employee();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		// Department department=new Department();
		// Role role=new Role();
		// employee.setName("David");
		// department.setId(2);
		// employee.setDepartmentId(department);
		// role.setId(1);
		// employee.setRoleId(role);
		// employee.setEmailId("david@gmail");
		// employee.setPassword("aaccbb");
		// employeeDAO.save(employee);
//		List<Employee> listAll = employeeDAO.listAll();
//		Iterator<Employee> i = listAll.iterator();
//		while (i.hasNext()) {
//			Employee emp = (Employee) i.next();
//			System.out.println(emp.getId() + "\t" + emp.getDepartmentId().getId() + "\t" + emp.getRoleId().getId()
//					+ "\t" + emp.getName() + "\t" + emp.getEmailId() + "\t" + emp.getPassword() + "\t"
//					+ emp.getActive());

//		}
//		System.out.println(employeeDAO.retreiveRoleNameforEmployee(3));
//		System.out.println(employeeDAO.retrieveEmployeeId("david@gmail"));
System.out.println(employeeDAO.listByDepartmentId(2).getEmailId());
	}

}
