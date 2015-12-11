package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyNetaccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyNetaccount
 * @author MyEclipse Persistence Tools
 */

public class PmWkyNetaccountDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyNetaccountDAO.class);
	// property constants
	public static final String ACCOUNT_NAME = "accountName";
	public static final String ACCOUNT_PASSWORD = "accountPassword";
	public static final String ACCOUNT_SECPASSWORD = "accountSecpassword";
	public static final String ACCOUNT_LRSJ = "accountLrsj";
	public static final String ACCOUNT_LRR = "accountLrr";
	public static final String ACCOUNT_LRRXM = "accountLrrxm";
	public static final String ACCOUNT_SFKY = "accountSfky";
	public static final String ACCOUNT_NETKEYWORDS = "accountNetkeywords";
	public static final String ACCOUNT_NETURL = "accountNeturl";
	public static final String ACCOUNT_BZ = "accountBz";

	public void save(PmWkyNetaccount transientInstance) {
		log.debug("saving PmWkyNetaccount instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyNetaccount persistentInstance) {
		log.debug("deleting PmWkyNetaccount instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyNetaccount findById(java.lang.String id) {
		log.debug("getting PmWkyNetaccount instance with id: " + id);
		try {
			PmWkyNetaccount instance = (PmWkyNetaccount) getSession().get(
					"com.sys.pm.po.PmWkyNetaccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyNetaccount instance) {
		log.debug("finding PmWkyNetaccount instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyNetaccount")
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
		log.debug("finding PmWkyNetaccount instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PmWkyNetaccount as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAccountName(Object accountName) {
		return findByProperty(ACCOUNT_NAME, accountName);
	}

	public List findByAccountPassword(Object accountPassword) {
		return findByProperty(ACCOUNT_PASSWORD, accountPassword);
	}

	public List findByAccountSecpassword(Object accountSecpassword) {
		return findByProperty(ACCOUNT_SECPASSWORD, accountSecpassword);
	}

	public List findByAccountLrsj(Object accountLrsj) {
		return findByProperty(ACCOUNT_LRSJ, accountLrsj);
	}

	public List findByAccountLrr(Object accountLrr) {
		return findByProperty(ACCOUNT_LRR, accountLrr);
	}

	public List findByAccountLrrxm(Object accountLrrxm) {
		return findByProperty(ACCOUNT_LRRXM, accountLrrxm);
	}

	public List findByAccountSfky(Object accountSfky) {
		return findByProperty(ACCOUNT_SFKY, accountSfky);
	}

	public List findByAccountNetkeywords(Object accountNetkeywords) {
		return findByProperty(ACCOUNT_NETKEYWORDS, accountNetkeywords);
	}

	public List findByAccountNeturl(Object accountNeturl) {
		return findByProperty(ACCOUNT_NETURL, accountNeturl);
	}

	public List findByAccountBz(Object accountBz) {
		return findByProperty(ACCOUNT_BZ, accountBz);
	}

	public List findAll() {
		log.debug("finding all PmWkyNetaccount instances");
		try {
			String queryString = "from PmWkyNetaccount";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyNetaccount merge(PmWkyNetaccount detachedInstance) {
		log.debug("merging PmWkyNetaccount instance");
		try {
			PmWkyNetaccount result = (PmWkyNetaccount) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyNetaccount instance) {
		log.debug("attaching dirty PmWkyNetaccount instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyNetaccount instance) {
		log.debug("attaching clean PmWkyNetaccount instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}