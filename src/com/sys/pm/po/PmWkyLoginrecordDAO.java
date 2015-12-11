package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyLoginrecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyLoginrecord
 * @author MyEclipse Persistence Tools
 */

public class PmWkyLoginrecordDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyLoginrecordDAO.class);
	// property constants
	public static final String RECORD_LOGINIP = "recordLoginip";
	public static final String RECORD_USERNANE = "recordUsernane";
	public static final String RECORD_LOGINTIME = "recordLogintime";

	public void save(PmWkyLoginrecord transientInstance) {
		log.debug("saving PmWkyLoginrecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyLoginrecord persistentInstance) {
		log.debug("deleting PmWkyLoginrecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyLoginrecord findById(java.lang.String id) {
		log.debug("getting PmWkyLoginrecord instance with id: " + id);
		try {
			PmWkyLoginrecord instance = (PmWkyLoginrecord) getSession().get(
					"com.sys.pm.po.PmWkyLoginrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyLoginrecord instance) {
		log.debug("finding PmWkyLoginrecord instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyLoginrecord")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PmWkyLoginrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PmWkyLoginrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRecordLoginip(Object recordLoginip) {
		return findByProperty(RECORD_LOGINIP, recordLoginip);
	}

	public List findByRecordUsernane(Object recordUsernane) {
		return findByProperty(RECORD_USERNANE, recordUsernane);
	}

	public List findByRecordLogintime(Object recordLogintime) {
		return findByProperty(RECORD_LOGINTIME, recordLogintime);
	}

	public List findAll() {
		log.debug("finding all PmWkyLoginrecord instances");
		try {
			String queryString = "from PmWkyLoginrecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyLoginrecord merge(PmWkyLoginrecord detachedInstance) {
		log.debug("merging PmWkyLoginrecord instance");
		try {
			PmWkyLoginrecord result = (PmWkyLoginrecord) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyLoginrecord instance) {
		log.debug("attaching dirty PmWkyLoginrecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyLoginrecord instance) {
		log.debug("attaching clean PmWkyLoginrecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}