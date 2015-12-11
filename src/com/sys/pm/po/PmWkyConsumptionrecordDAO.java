package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyConsumptionrecord entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyConsumptionrecord
 * @author MyEclipse Persistence Tools
 */

public class PmWkyConsumptionrecordDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(PmWkyConsumptionrecordDAO.class);
	// property constants
	public static final String RECORD_DEPOSITTYPE = "recordDeposittype";
	public static final String RECORD_TYPE = "recordType";
	public static final String RECORD_CONSUMPTIONTYPE = "recordConsumptiontype";
	public static final String RECORD_CONSUMPTIONCREDIT = "recordConsumptioncredit";
	public static final String RECORD_REMARKS = "recordRemarks";
	public static final String RECORD_USERNANE = "recordUsernane";
	public static final String RECORD_TIME = "recordTime";

	public void save(PmWkyConsumptionrecord transientInstance) {
		log.debug("saving PmWkyConsumptionrecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyConsumptionrecord persistentInstance) {
		log.debug("deleting PmWkyConsumptionrecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyConsumptionrecord findById(java.lang.String id) {
		log.debug("getting PmWkyConsumptionrecord instance with id: " + id);
		try {
			PmWkyConsumptionrecord instance = (PmWkyConsumptionrecord) getSession()
					.get("com.sys.pm.po.PmWkyConsumptionrecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyConsumptionrecord instance) {
		log.debug("finding PmWkyConsumptionrecord instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyConsumptionrecord")
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
		log.debug("finding PmWkyConsumptionrecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PmWkyConsumptionrecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRecordDeposittype(Object recordDeposittype) {
		return findByProperty(RECORD_DEPOSITTYPE, recordDeposittype);
	}

	public List findByRecordType(Object recordType) {
		return findByProperty(RECORD_TYPE, recordType);
	}

	public List findByRecordConsumptiontype(Object recordConsumptiontype) {
		return findByProperty(RECORD_CONSUMPTIONTYPE, recordConsumptiontype);
	}

	public List findByRecordConsumptioncredit(Object recordConsumptioncredit) {
		return findByProperty(RECORD_CONSUMPTIONCREDIT, recordConsumptioncredit);
	}

	public List findByRecordRemarks(Object recordRemarks) {
		return findByProperty(RECORD_REMARKS, recordRemarks);
	}

	public List findByRecordUsernane(Object recordUsernane) {
		return findByProperty(RECORD_USERNANE, recordUsernane);
	}

	public List findByRecordTime(Object recordTime) {
		return findByProperty(RECORD_TIME, recordTime);
	}

	public List findAll() {
		log.debug("finding all PmWkyConsumptionrecord instances");
		try {
			String queryString = "from PmWkyConsumptionrecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyConsumptionrecord merge(PmWkyConsumptionrecord detachedInstance) {
		log.debug("merging PmWkyConsumptionrecord instance");
		try {
			PmWkyConsumptionrecord result = (PmWkyConsumptionrecord) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyConsumptionrecord instance) {
		log.debug("attaching dirty PmWkyConsumptionrecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyConsumptionrecord instance) {
		log.debug("attaching clean PmWkyConsumptionrecord instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List findByFRecordTime(int start, int limit, String startTime,
			String endTime, String userName) {
		log.debug("finding PmWkyConsumptionrecord instance between date");
		try {
			String queryString = "from PmWkyConsumptionrecord as model where model.recordTime between ? and ? and model.recordUsernane = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, startTime);
			queryObject.setParameter(1, endTime);
			queryObject.setParameter(2, userName);
			queryObject.setFirstResult(start);
			queryObject.setMaxResults(limit);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}

	}
	public int countByFRecordTime(String startTime, String endTime, String userName) {
		log.debug("select count(*) finding PmWkyConsumptionrecord instance between date");
		try {
			String queryString = "select count(*) from PmWkyConsumptionrecord as model where model.recordTime between ? and ? and model.recordUsernane = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, startTime);
			queryObject.setParameter(1, endTime);
			queryObject.setParameter(2, userName);
			return Integer.valueOf(queryObject.list().get(0).toString());
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}

	}
	public List findByDate(String startTime,String endTime,String userName){
		log.debug("select count(*) finding PmWkyConsumptionrecord instance between date");
		try {
			
			String queryString = " from PmWkyConsumptionrecord as model where model.recordTime between ? and ? and model.recordUsernane = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, startTime);
			queryObject.setParameter(1, endTime);
			queryObject.setParameter(2, userName);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}