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

import za.co.iclub.pss.orm.bean.IclubSystemType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSystemType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSystemType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSystemTypeDAO {
	private static final Logger log = Logger.getLogger(IclubSystemTypeDAO.class);
	// property constants
	public static final String ST_SHORT_DESC = "stShortDesc";
	public static final String ST_LONG_DESC = "stLongDesc";
	public static final String ST_STATUS = "stStatus";

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

	public void save(IclubSystemType transientInstance) {
		log.debug("saving IclubSystemType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSystemType persistentInstance) {
		log.debug("deleting IclubSystemType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSystemType findById(java.lang.Long id) {
		log.debug("getting IclubSystemType instance with id: " + id);
		try {
			IclubSystemType instance = (IclubSystemType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSystemType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSystemType> findByExample(IclubSystemType instance) {
		log.debug("finding IclubSystemType instance by example");
		try {
			List<IclubSystemType> results = (List<IclubSystemType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSystemType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSystemType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSystemType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSystemType> findByStShortDesc(Object stShortDesc) {
		return findByProperty(ST_SHORT_DESC, stShortDesc);
	}

	public List<IclubSystemType> findByStLongDesc(Object stLongDesc) {
		return findByProperty(ST_LONG_DESC, stLongDesc);
	}

	public List<IclubSystemType> findByStStatus(Object stStatus) {
		return findByProperty(ST_STATUS, stStatus);
	}

	public List findAll() {
		log.debug("finding all IclubSystemType instances");
		try {
			String queryString = "from IclubSystemType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSystemType merge(IclubSystemType detachedInstance) {
		log.debug("merging IclubSystemType instance");
		try {
			IclubSystemType result = (IclubSystemType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSystemType instance) {
		log.debug("attaching dirty IclubSystemType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSystemType instance) {
		log.debug("attaching clean IclubSystemType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSystemTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSystemTypeDAO) ctx.getBean("IclubSystemTypeDAO");
	}
}