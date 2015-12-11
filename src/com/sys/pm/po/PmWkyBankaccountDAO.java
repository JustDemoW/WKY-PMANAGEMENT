package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyBankaccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyBankaccount
 * @author MyEclipse Persistence Tools
 */

public class PmWkyBankaccountDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyBankaccountDAO.class);
	// property constants
	public static final String BANK_ACCOUNT = "bankAccount";
	public static final String BANK_PASSWORD = "bankPassword";
	public static final String BANK_NAME = "bankName";
	public static final String BANK_TYPE = "bankType";

	public void save(PmWkyBankaccount transientInstance) {
		log.debug("saving PmWkyBankaccount instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyBankaccount persistentInstance) {
		log.debug("deleting PmWkyBankaccount instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyBankaccount findById(java.lang.String id) {
		log.debug("getting PmWkyBankaccount instance with id: " + id);
		try {
			PmWkyBankaccount instance = (PmWkyBankaccount) getSession().get(
					"com.sys.pm.po.PmWkyBankaccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyBankaccount instance) {
		log.debug("finding PmWkyBankaccount instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sys.pm.po.PmWkyBankaccount").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PmWkyBankaccount instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PmWkyBankaccount as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBankAccount(Object bankAccount) {
		return findByProperty(BANK_ACCOUNT, bankAccount);
	}

	public List findByBankPassword(Object bankPassword) {
		return findByProperty(BANK_PASSWORD, bankPassword);
	}

	public List findByBankName(Object bankName) {
		return findByProperty(BANK_NAME, bankName);
	}

	public List findByBankType(Object bankType) {
		return findByProperty(BANK_TYPE, bankType);
	}

	public List findAll() {
		log.debug("finding all PmWkyBankaccount instances");
		try {
			String queryString = "from PmWkyBankaccount";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyBankaccount merge(PmWkyBankaccount detachedInstance) {
		log.debug("merging PmWkyBankaccount instance");
		try {
			PmWkyBankaccount result = (PmWkyBankaccount) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyBankaccount instance) {
		log.debug("attaching dirty PmWkyBankaccount instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyBankaccount instance) {
		log.debug("attaching clean PmWkyBankaccount instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}