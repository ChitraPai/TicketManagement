package com.revature.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Role;
import com.revature.util.ConnectionUtil;

public class RoleDAO implements DAO<Role> {

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Role role) throws PersistenceException {
		try {
			String sql = "Insert into roles (name) values(?)";
			Object[] params = { role.getName() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Given name already exists", e);
		}
	}

	@Override
	public void update(Role role) {
		String sql = "update roles set name=? where id=? ";
		Object[] params = { role.getName(), role.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateAsInactive(Role role) {
		String sql = "update departments set active=? where id=?";
		Object[] params = { role.getActive(), role.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Role> listAll() {
		String sql = "select id,name,active from roles";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Role role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setActive(rs.getBoolean("active"));
			return role;

		});
	}

}
