package ru.javarush.winter2014.dao;

import java.io.Serializable;
import java.util.List;

public interface CRUDDao {
	<T> List<T> getAll(Class<T> klass);

	<T> void Save(T klass);

	<T> T findByPrimaryKey(Class<T> klass, Serializable id);

	<T> T GetUniqueEntityByNamedQuery(String query, Object... params);

	<T> List<T> GetListByNamedQuery(String query, Object... params);

	<T> void delete(T klass);

	<T> Long getQueryCount(String query, Object... params);
	
}
