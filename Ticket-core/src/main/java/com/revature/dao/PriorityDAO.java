package com.revature.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.Priority;
import com.revature.util.ConnectionUtil;

public class PriorityDAO implements DAO<Priority> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override
	public void save(Priority priority) throws PersistenceException {
		try {
			String sql = "Insert into priority (name) values(?)";
			Object[] params = { priority.getName() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("Given name already exists", e);
		}
	}

	@Override
	public void update(Priority priority) {
		String sql = "update priority set name=? where id=? ";
		Object[] params = { priority.getName(), priority.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateAsInactive(Priority priority) {
		String sql = "update departments set active=? where id=?";
		Object[] params = { priority.getActive(), priority.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Priority> listAll() {
		String sql = "select id,name,active from priority";
		Priority priority = new Priority();
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			priority.setId(rs.getInt("id"));
			priority.setName(rs.getString("name"));
			priority.setActive(rs.getBoolean("active"));
			return priority;

		});
	}

	public Priority retrievePriorityId(String name) throws PersistenceException {
		try {
			String sql = "select id from priority where name=?";
			Object[] params = { name };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				Priority priority = new Priority();
				priority.setId(rs.getInt("id"));
				return priority;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Give email id doesnt exist", e);
		}
	}
}
