package com.sys.pm.po;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * PmWkyModule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sys.pm.po.PmWkyModule
 * @author MyEclipse Persistence Tools
 */

public class PmWkyModuleDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PmWkyModuleDAO.class);
	// property constants
	public static final String DIRECTORY = "directory";
	public static final String AUTHOR = "author";
	public static final String VERSION = "version";
	public static final String URL = "url";
	public static final String CLASS_NAME = "className";
	public static final String MODULE_TYPE = "moduleType";
	public static final String MODULE_ID = "moduleId";
	public static final String MENU_PATH = "menuPath";
	public static final String LAUNCHER_ICON_CLS = "launcherIconCls";
	public static final String LAUNCHER_SHORTCUT_ICON_CLS = "launcherShortcutIconCls";
	public static final String LAUNCHER_TEXT = "launcherText";
	public static final String LAUNCHER_TOOLTIP = "launcherTooltip";
	public static final String ACTIVE = "active";
	public static final String PROLOAD = "proload";
	public static final String DESCRIPTION = "description";
	public static final String SORT_ORDER = "sortOrder";

	public void save(PmWkyModule transientInstance) {
		log.debug("saving PmWkyModule instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PmWkyModule persistentInstance) {
		log.debug("deleting PmWkyModule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PmWkyModule findById(java.lang.String id) {
		log.debug("getting PmWkyModule instance with id: " + id);
		try {
			PmWkyModule instance = (PmWkyModule) getSession().get(
					"com.sys.pm.po.PmWkyModule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PmWkyModule instance) {
		log.debug("finding PmWkyModule instance by example");
		try {
			List results = getSession()
					.createCriteria("com.sys.pm.po.PmWkyModule")
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
		log.debug("finding PmWkyModule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PmWkyModule as model where model."
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

	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByClassName(Object className) {
		return findByProperty(CLASS_NAME, className);
	}

	public List findByModuleType(Object moduleType) {
		return findByProperty(MODULE_TYPE, moduleType);
	}

	public List findByModuleId(Object moduleId) {
		return findByProperty(MODULE_ID, moduleId);
	}

	public List findByMenuPath(Object menuPath) {
		return findByProperty(MENU_PATH, menuPath);
	}

	public List findByLauncherIconCls(Object launcherIconCls) {
		return findByProperty(LAUNCHER_ICON_CLS, launcherIconCls);
	}

	public List findByLauncherShortcutIconCls(Object launcherShortcutIconCls) {
		return findByProperty(LAUNCHER_SHORTCUT_ICON_CLS,
				launcherShortcutIconCls);
	}

	public List findByLauncherText(Object launcherText) {
		return findByProperty(LAUNCHER_TEXT, launcherText);
	}

	public List findByLauncherTooltip(Object launcherTooltip) {
		return findByProperty(LAUNCHER_TOOLTIP, launcherTooltip);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByProload(Object proload) {
		return findByProperty(PROLOAD, proload);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findBySortOrder(Object sortOrder) {
		return findByProperty(SORT_ORDER, sortOrder);
	}

	public List findAll() {
		log.debug("finding all PmWkyModule instances");
		try {
			String queryString = "from PmWkyModule";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PmWkyModule merge(PmWkyModule detachedInstance) {
		log.debug("merging PmWkyModule instance");
		try {
			PmWkyModule result = (PmWkyModule) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PmWkyModule instance) {
		log.debug("attaching dirty PmWkyModule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PmWkyModule instance) {
		log.debug("attaching clean PmWkyModule instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}