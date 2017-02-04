package com.revature.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Department;
import com.revature.util.ConnectionUtil;

public class DepartmentDAO implements DAO<Department> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Department department) throws PersistenceException {
		try {
			String sql = "Insert into departments (name) values(?)";
			Object[] params = { department.getName() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Given name already exists", e);
		}
	}

	@Override
	public void update(Department department) {
		String sql = "update departments set name=? where id=? ";
		Object[] params = { department.getName(), department.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateAsInactive(Department department) {
		String sql = "update departments set active=? where id=?";
		Object[] params = { department.getActive(), department.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Department> listAll() {
		String sql = "select id,name,active from departments";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Department department = new Department();
			department.setId(rs.getInt("id"));
			department.setName(rs.getString("name"));
			department.setActive(rs.getBoolean("active"));
			return department;

		});
	}

	public Department retrieveDepartmentId(String name) throws PersistenceException {
		try {
			String sql = "select id from departments where name=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Department department = new Department();
				department.setId(rs.getInt("id"));
				return department;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given department name doesnt exist", e);
		}

	}
}
