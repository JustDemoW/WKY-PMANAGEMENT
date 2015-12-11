package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyModuleFile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyModuleFile
 * @author MyEclipse Persistence Tools
 */

public class PmWkyModuleFileDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyModuleFileDAO.class);
	// property constants
	public static final String DIRECTORY = "directory";

	public void save(PmWkyModuleFile transientInstance) {
		log.debug("saving PmWkyModuleFile instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyModuleFile persistentInstance) {
		log.debug("deleting PmWkyModuleFile instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyModuleFile findById(com.sys.pm.po.PmWkyModuleFileId id) {
		log.debug("getting PmWkyModuleFile instance with id: " + id);
		try {
			PmWkyModuleFile instance = (PmWkyModuleFile) getSession().get(
					"com.sys.pm.po.PmWkyModuleFile", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyModuleFile instance) {
		log.debug("finding PmWkyModuleFile instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyModuleFile")
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
		log.debug("finding PmWkyModuleFile instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PmWkyModuleFile as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDirectory(Object directory) {
		return findByProperty(DIRECTORY, directory);
	}

	public List findAll() {
		log.debug("finding all PmWkyModuleFile instances");
		try {
			String queryString = "from PmWkyModuleFile";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyModuleFile merge(PmWkyModuleFile detachedInstance) {
		log.debug("merging PmWkyModuleFile instance");
		try {
			PmWkyModuleFile result = (PmWkyModuleFile) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyModuleFile instance) {
		log.debug("attaching dirty PmWkyModuleFile instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyModuleFile instance) {
		log.debug("attaching clean PmWkyModuleFile instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}