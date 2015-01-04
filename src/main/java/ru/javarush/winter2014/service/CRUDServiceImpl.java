package ru.javarush.winter2014.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.javarush.winter2014.dao.CRUDDao;

@Service
public class CRUDServiceImpl implements CRUDService {

	@Autowired
	private CRUDDao CRUDDao;

	@Transactional(readOnly = true)
	public <T> List<T> getAll(Class<T> klass) {
		return CRUDDao.getAll(klass);
	}

	@Transactional
	public <T> void Save(T klass) throws DataAccessException {
		CRUDDao.Save(klass);
	}

	@Transactional
	public <T> void delete(T klass) throws DataAccessException {
		CRUDDao.delete(klass);
	}

	@Transactional
	public <T> T GetUniqueEntityByNamedQuery(String query, Object... params) {
		return CRUDDao.GetUniqueEntityByNamedQuery(query, params);
	}

	@Transactional
	public <T> List<T> GetListByNamedQuery(String query, Object... params) {
		return CRUDDao.GetListByNamedQuery(query, params);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> Long getQueryCount(String query, Object... params) {
		return CRUDDao.getQueryCount(query, params);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> T findByPrimaryKey(Class<T> klass, Serializable id) {
		return CRUDDao.findByPrimaryKey(klass, id);
	}

}