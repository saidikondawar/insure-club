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

import za.co.iclub.pss.orm.bean.IclubAccessType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubAccessType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubAccessType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubAccessTypeDAO {
	private static final Logger log = Logger.getLogger(IclubAccessTypeDAO.class);
	// property constants
	public static final String AT_SHORT_DESC = "atShortDesc";
	public static final String AT_LONG_DESC = "atLongDesc";
	public static final String AT_STATUS = "atStatus";

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

	public void save(IclubAccessType transientInstance) {
		log.debug("saving IclubAccessType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubAccessType persistentInstance) {
		log.debug("deleting IclubAccessType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubAccessType findById(java.lang.Long id) {
		log.debug("getting IclubAccessType instance with id: " + id);
		try {
			IclubAccessType instance = (IclubAccessType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubAccessType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubAccessType> findByExample(IclubAccessType instance) {
		log.debug("finding IclubAccessType instance by example");
		try {
			List<IclubAccessType> results = (List<IclubAccessType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubAccessType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubAccessType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubAccessType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubAccessType> findByAtShortDesc(Object atShortDesc) {
		return findByProperty(AT_SHORT_DESC, atShortDesc);
	}

	public List<IclubAccessType> findByAtLongDesc(Object atLongDesc) {
		return findByProperty(AT_LONG_DESC, atLongDesc);
	}

	public List<IclubAccessType> findByAtStatus(Object atStatus) {
		return findByProperty(AT_STATUS, atStatus);
	}

	public List findAll() {
		log.debug("finding all IclubAccessType instances");
		try {
			String queryString = "from IclubAccessType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubAccessType merge(IclubAccessType detachedInstance) {
		log.debug("merging IclubAccessType instance");
		try {
			IclubAccessType result = (IclubAccessType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubAccessType instance) {
		log.debug("attaching dirty IclubAccessType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubAccessType instance) {
		log.debug("attaching clean IclubAccessType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubAccessTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubAccessTypeDAO) ctx.getBean("IclubAccessTypeDAO");
	}
}