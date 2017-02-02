package com.revature.dao;

import java.util.List;

import com.revature.exception.PersistenceException;

public interface DAO<T> {
	void save(T t) throws PersistenceException;

	void update(T t);

	void updateAsInactive(T t);

	List<T> listAll();
}
