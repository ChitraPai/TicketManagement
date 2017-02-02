package com.revature.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Role;
import com.revature.util.ConnectionUtil;

public class EmployeeDAO implements DAO<Employee> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Employee employee) throws PersistenceException {
		try {
			String sql = "Insert into employees(department_id,role_id,name,email_id,password) values(?,?,?,?,?)";
			Object[] params = { employee.getDepartmentId().getId(), employee.getRoleId().getId(), employee.getName(),
					employee.getEmailId(), employee.getPassword() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Given email id already exists", e);
		}
	}

	@Override
	public void update(Employee employee) {

		String sql = "update employees set password=? where email_id=? ";
		Object[] params = { employee.getPassword(), employee.getEmailId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateAsInactive(Employee employee) {
		String sql = "update employees set active=? where id=?";
		Object[] params = { employee.getActive(), employee.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Employee> listAll() {
		String sql = "select id,department_id,role_id,name,email_id,password,active from employees";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			Department department = new Department();
			department.setId(rs.getInt("department_id"));
			employee.setDepartmentId(department);
			Role role = new Role();
			role.setId(rs.getInt("role_id"));
			employee.setRoleId(role);
			employee.setName(rs.getString("name"));
			employee.setEmailId(rs.getString("email_id"));
			employee.setPassword(rs.getString("password"));
			employee.setActive(rs.getBoolean("active"));
			return employee;

		});

	}

}