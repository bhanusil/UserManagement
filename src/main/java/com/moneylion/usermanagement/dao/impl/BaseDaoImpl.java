package com.moneylion.usermanagement.dao.impl;

import com.moneylion.usermanagement.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * The Class BaseDaoImpl.
 *
 * @param <T> the generic type
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	/** The type. */
	Class<T> type;

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Instantiates a new base com.trading.dao impl.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public T createAndGet(T model) {
		getCurrentSession().save(model);
		getCurrentSession().refresh(model);
		return model;
	}

	/* (non-Javadoc)
	 * @see com.trading.BaseDao#create(java.lang.Object)
	 */
	public long create(T model) {
		Long id = (Long) getCurrentSession().save(model);
		return id.longValue();
	}

	/* (non-Javadoc)
	 * @see com.trading.BaseDao#read(java.lang.Long)
	 */
	public T read(Long id) {
		return getCurrentSession().get(type, id);
	}

	/* (non-Javadoc)
	 * @see com.trading.BaseDao#readAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Criteria criteria = getCurrentSession().createCriteria(type);
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.trading.BaseDao#update(java.lang.Object)
	 */
	public void update(T model) {
		getCurrentSession().update(model);
	}


	/**
	 * Gets the current session.
	 *
	 * @return the current session
	 */
	public Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

}
