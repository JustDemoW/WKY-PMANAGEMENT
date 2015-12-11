package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyUser
 * @author MyEclipse Persistence Tools
 */

public class PmWkyUserDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyUserDAO.class);
	// property constants
	public static final String USER_PASSWORD = "userPassword";
	public static final String USER_NICKNAME = "userNickname";
	public static final String USER_SEX = "userSex";
	public static final String USER_QX = "userQx";
	public static final String USER_LASTLOGINTIME = "userLastlogintime";
	public static final String USER_LASTLOGINIP = "userLastloginip";

	public void save(PmWkyUser transientInstance) {
		log.debug("saving PmWkyUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyUser persistentInstance) {
		log.debug("deleting PmWkyUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyUser findById(java.lang.String id) {
		log.debug("getting PmWkyUser instance with id: " + id);
		try {
			PmWkyUser instance = (PmWkyUser) getSession().get(
					"com.sys.pm.po.PmWkyUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyUser instance) {
		log.debug("finding PmWkyUser instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyUser")
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
		log.debug("finding PmWkyUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PmWkyUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findByUserNickname(Object userNickname) {
		return findByProperty(USER_NICKNAME, userNickname);
	}

	public List findByUserSex(Object userSex) {
		return findByProperty(USER_SEX, userSex);
	}

	public List findByUserQx(Object userQx) {
		return findByProperty(USER_QX, userQx);
	}

	public List findByUserLastlogintime(Object userLastlogintime) {
		return findByProperty(USER_LASTLOGINTIME, userLastlogintime);
	}

	public List findByUserLastloginip(Object userLastloginip) {
		return findByProperty(USER_LASTLOGINIP, userLastloginip);
	}

	public List findAll() {
		log.debug("finding all PmWkyUser instances");
		try {
			String queryString = "from PmWkyUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyUser merge(PmWkyUser detachedInstance) {
		log.debug("merging PmWkyUser instance");
		try {
			PmWkyUser result = (PmWkyUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyUser instance) {
		log.debug("attaching dirty PmWkyUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyUser instance) {
		log.debug("attaching clean PmWkyUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}