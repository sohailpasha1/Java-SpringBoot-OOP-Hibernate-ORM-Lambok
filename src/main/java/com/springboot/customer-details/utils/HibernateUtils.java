package com.customerdetails.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUtils {

	@Autowired
	public SessionFactory sessionFactory;

	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public void closeSession(Session currentSession) {
		try {
			if (currentSession != null) {
				sessionFactory.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			currentSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T save(T entity) {
		Session session = null;
		Serializable id = null;
		try {
			session = getSession();
			id = session.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeSession(session);
		}
		return (T) id;
	}

	public <T> T saveEntity(T entity) {
		Transaction tx = null;
		try (Session session = getSession()) {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
			return entity;
		} catch (Exception e) {
			e.getMessage();
			if (tx != null) {
				tx.rollback();
			}
		}
		return null;
	}

	public <T> void update(T entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			closeSession(session);
		}
	}

	public <T> T delete(T entity) {
		Session session = null;
		try {
			session = getSession();
			session.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public <T> T findEntityById(Class<T> entityClass, Serializable primaryId) {
		try (Session session = getSession()) {
			Object dataObject = session.get(entityClass, primaryId);
			return (T) dataObject;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass) {
		try (Session session = getSession()) {
			return session.createCriteria(entityClass).list();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T loadEntityByCriteria(Class<T> entityClass, String propertyName, Serializable serializableValue) {
		try (Session session = getSession()) {
			@SuppressWarnings("deprecation")
			Object dataObject = session.createCriteria(entityClass)
					.add(Restrictions.eq(propertyName, serializableValue)).uniqueResult();
			return (T) dataObject;

		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public <T> T findEntityByCriteria(Class<T> entityClass, String primaryPropertyName, String primaryValue) {
		try (Session session = getSession()) {
			Object dataObject = session.createCriteria(entityClass)
					.add(Restrictions.eq(primaryPropertyName, primaryValue)).uniqueResult();
			return (T) dataObject;
		}
	}

	public Query createQuery(String hqlQuery) {
		Query query = null;
		try (Session session = getSession()) {
			query = session.createQuery(hqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	public <T> T findEntityByCriteria(Class<T> entityClass, String primaryPropertyName1, String primaryPropertyName2,
			String primaryPropertyName3, Serializable primaryId1, Serializable primaryId2, Serializable primaryId3) {
		try (Session session = getSession()) {
			Object dataObject = session.createCriteria(entityClass)
					.add(Restrictions.eq(primaryPropertyName1, primaryId1))
					.add(Restrictions.eq(primaryPropertyName2, primaryId2))
					.add(Restrictions.eq(primaryPropertyName3, primaryId3)).uniqueResult();
			return (T) dataObject;
		}
	}

	@SuppressWarnings("deprecation")
	public <T> List<T> getAllEntities(Class<T> clazz) {
		try (Session session = getSession()) {
			String q = "SELECT t FROM " + clazz.getSimpleName() + "  t";
			Query<T> query = session.createQuery(q, clazz);
			return query.getResultList();
		}
	}

	public <T> T saveOrUpdateEntity(T entity) {
		Transaction tx = null;
		try (Session session = getSession()) {
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			return entity;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
	}
}
