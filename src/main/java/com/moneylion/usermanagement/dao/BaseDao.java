package com.moneylion.usermanagement.dao;

import java.util.List;

/**
 * The Interface BaseDao.
 *
 * @param <T> the generic type
 */
public interface BaseDao<T> {

	/**
	 * Creates and return created object.
	 *
	 * @param model the type
	 */
	public T createAndGet(T model);

	/**
	 * Creates the.
	 *
	 * @param type the type
	 */
	long create(T type);

	/**
	 * Read.
	 *
	 * @param id the id
	 * @return the t
	 */
	T read(Long id);

	/**
	 * Read all.
	 *
	 * @return the list
	 */
	public List<T> readAll();

	/**
	 * Update.
	 *
	 * @param type the type
	 */
	void update(T type);

}
