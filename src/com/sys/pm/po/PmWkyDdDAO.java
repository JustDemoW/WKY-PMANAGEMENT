package com.sys.pm.po;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyDd entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyDd
 * @author MyEclipse Persistence Tools
 */

public class PmWkyDdDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyDdDAO.class);
	// property constants
	public static final String DD_KEY = "ddKey";
	public static final String DD_CODE_VALUE = "ddCodeValue";
	public static final String DD_VALUE = "ddValue";
	public static final String DD_PINYIN = "ddPinyin";

	public void save(PmWkyDd transientInstance) {
		log.debug("saving PmWkyDd instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyDd persistentInstance) {
		log.debug("deleting PmWkyDd instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyDd findById(java.lang.String id) {
		log.debug("getting PmWkyDd instance with id: " + id);
		try {
			PmWkyDd instance = (PmWkyDd) getSession().get(
					"com.sys.pm.po.PmWkyDd", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List findByDdKeyAndDdCodeValue(java.lang.String key,java.lang.String codeValue) {
		log.debug("from PmWkyDd as model where model.ddKey = "+key+" and model.ddCodeValue = "+codeValue);
		try {
			String queryString = "from PmWkyDd as model where model.ddKey = ? and model.ddCodeValue = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, key);
			queryObject.setParameter(1, codeValue);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByDdKeyAndDdValue(java.lang.String key,java.lang.String Value) {
		log.debug("from PmWkyDd as model where model.ddKey = "+key+" and model.ddValue = "+Value);
		try {
			String queryString = "from PmWkyDd as model where model.ddKey = ? and model.ddValue = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, key);
			queryObject.setParameter(1, Value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByExample(PmWkyDd instance) {
		log.debug("finding PmWkyDd instance by example");
		try {
			List results = getSession().createCriteria("com.sys.pm.po.PmWkyDd")
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
		log.debug("finding PmWkyDd instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PmWkyDd as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDdKey(Object ddKey) {
		return findByProperty(DD_KEY, ddKey);
	}

	public List findByDdCodeValue(Object ddCodeValue) {
		return findByProperty(DD_CODE_VALUE, ddCodeValue);
	}

	public List findByDdValue(Object ddValue) {
		return findByProperty(DD_VALUE, ddValue);
	}

	public List findByDdPinyin(Object ddPinyin) {
		return findByProperty(DD_PINYIN, ddPinyin);
	}

	public List findAll() {
		log.debug("finding all PmWkyDd instances");
		try {
			String queryString = "from PmWkyDd";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyDd merge(PmWkyDd detachedInstance) {
		log.debug("merging PmWkyDd instance");
		try {
			PmWkyDd result = (PmWkyDd) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyDd instance) {
		log.debug("attaching dirty PmWkyDd instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyDd instance) {
		log.debug("attaching clean PmWkyDd instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}