package ru.javarush.winter2014.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class CRUDDaoImpl implements CRUDDao {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> klass) {
		return getCurrentSession().createQuery("from " + klass.getName())
				.list();
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> void Save(T klass) throws DataAccessException {
		getCurrentSession().saveOrUpdate(klass);
	}

	public <T> void delete(T klass) throws DataAccessException {
		getCurrentSession().delete(klass);

	}

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	@SuppressWarnings("unchecked")
	public <T> T findByPrimaryKey(Class<T> klass, Serializable id) {
		return (T) getCurrentSession().get(klass, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> GetListByNamedQuery(String query, Object... params) {
		Query q = getCurrentSession().getNamedQuery(query);

		int i = 1;
		String arg = "arg";
		if (params != null) {
			for (Object o : params) {
				if (o != null) {
					q.setParameter(arg + i, o);
					i++;
				}
			}
		}

		List<T> list = (List<T>) q.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> T GetUniqueEntityByNamedQuery(String query, Object... params) {

		Query q = getCurrentSession().getNamedQuery(query);

		int i = 1;
		String arg = "arg";
		for (Object o : params) {
			q.setParameter(arg + i, o);
			i++;
		}

		List<T> results = q.list();

		T foundentity = null;
		if (!results.isEmpty()) {
			// ignores multiple results
			foundentity = results.get(0);
		}
		return foundentity;
	}

	public <T> Long getQueryCount(String query, Object... params) {

		Query q = getCurrentSession().getNamedQuery(query);
		int i = 1;
		String arg = "arg";
		Long count = (long) 0;

		if (params != null) {
			for (Object o : params) {
				if (o != null) {
					q.setParameter(arg + i, o);
					i++;
				}
			}
		}
		count = (Long) q.uniqueResult();
		return count;
	}
}