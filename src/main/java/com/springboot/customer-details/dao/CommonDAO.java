package com.customerdetails.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDAO {

	public <T> T save(T entity);

	public <T> void update(T entity);

	public <T> T delete(T entity);

	<T> T findEntityById(Class<T> entityClass, Serializable primaryId);

	public <T> List<T> loadEntity(Class<T> entityClass);

}
