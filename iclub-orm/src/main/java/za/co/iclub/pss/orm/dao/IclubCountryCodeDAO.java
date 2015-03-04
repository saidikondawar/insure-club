package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubCountryCode;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCountryCode entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCountryCode
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCountryCodeDAO {
	private static final Logger log = Logger.getLogger(IclubCountryCodeDAO.class);
	// property constants
	public static final String CC_SHORT_ID = "ccShortId";
	public static final String CC_ISO_ID = "ccIsoId";
	public static final String CC_NAME = "ccName";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(IclubCountryCode transientInstance) {
		log.debug("saving IclubCountryCode instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCountryCode persistentInstance) {
		log.debug("deleting IclubCountryCode instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCountryCode findById(java.lang.Integer id) {
		log.debug("getting IclubCountryCode instance with id: " + id);
		try {
			IclubCountryCode instance = (IclubCountryCode) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCountryCode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCountryCode> findByExample(IclubCountryCode instance) {
		log.debug("finding IclubCountryCode instance by example");
		try {
			List<IclubCountryCode> results = (List<IclubCountryCode>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCountryCode").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCountryCode instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCountryCode as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCountryCode> findByCcShortId(Object ccShortId) {
		return findByProperty(CC_SHORT_ID, ccShortId);
	}

	public List<IclubCountryCode> findByCcIsoId(Object ccIsoId) {
		return findByProperty(CC_ISO_ID, ccIsoId);
	}

	public List<IclubCountryCode> findByCcName(Object ccName) {
		return findByProperty(CC_NAME, ccName);
	}

	public List findAll() {
		log.debug("finding all IclubCountryCode instances");
		try {
			String queryString = "from IclubCountryCode";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCountryCode merge(IclubCountryCode detachedInstance) {
		log.debug("merging IclubCountryCode instance");
		try {
			IclubCountryCode result = (IclubCountryCode) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCountryCode instance) {
		log.debug("attaching dirty IclubCountryCode instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCountryCode instance) {
		log.debug("attaching clean IclubCountryCode instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUser(String userId) {
		log.debug("finding all IclubCountryCode instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubCountryCodeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCountryCodeDAO) ctx.getBean("IclubCountryCodeDAO");
	}
}