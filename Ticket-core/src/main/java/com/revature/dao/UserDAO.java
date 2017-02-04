package com.revature.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.exception.PersistenceException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDAO implements DAO<User> {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	@Override

	public void save(User user) throws PersistenceException {
		try {
			String sql = "Insert into users(name,email_id,password) values(?,?,?)";
			Object[] params = { user.getName(), user.getEmailId(), user.getPassword() };
			jdbcTemplate.update(sql, params);
		} catch (DuplicateKeyException  e) {
			throw new PersistenceException("Given email id already exists", e);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new PersistenceException("Given email id has already been registered ", e);
		}
	}

	@Override
	public void update(User user) {

		String sql = "update users set password=? where email_id=? ";
		Object[] params = { user.getPassword(), user.getEmailId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateAsInactive(User user) {
		String sql = "update users set active=? where id=?";
		Object[] params = { user.getActive(), user.getId() };
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<User> listAll() {
		String sql = "select id,name,email_id,password,active from users";

		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmailId(rs.getString("email_id"));
			user.setPassword(rs.getString("password"));
			user.setActive(rs.getBoolean("active"));
			return user;

		});
	}

	public String retrievePassword(String emailId) throws PersistenceException {
		try {
			String sql = "select password from users where email_id=?";
			Object[] params = { emailId };
			return jdbcTemplate.queryForObject(sql, params, String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given email id doesnt exist", e);
		}

	}

	public User retrieveUserId(String emailId) throws PersistenceException {
		try {
			String sql = "select id from users where email_id=?";
			Object[] params = { emailId };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
				User user = new User();
				user.setId(rs.getInt("id"));
				return user;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Given email id doesnt exist", e);
		}
	}

}
