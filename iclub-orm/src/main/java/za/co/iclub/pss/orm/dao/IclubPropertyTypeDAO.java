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

import za.co.iclub.pss.orm.bean.IclubPropertyType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPropertyType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPropertyType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPropertyTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubPropertyTypeDAO.class);
	// property constants
	public static final String PT_SHORT_DESC = "ptShortDesc";
	public static final String PT_LONG_DESC = "ptLongDesc";
	public static final String PT_STATUS = "ptStatus";

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

	public void save(IclubPropertyType transientInstance) {
		log.debug("saving IclubPropertyType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubPropertyType persistentInstance) {
		log.debug("deleting IclubPropertyType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubPropertyType findById(java.lang.Long id) {
		log.debug("getting IclubPropertyType instance with id: " + id);
		try {
			IclubPropertyType instance = (IclubPropertyType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubPropertyType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubPropertyType> findByExample(IclubPropertyType instance) {
		log.debug("finding IclubPropertyType instance by example");
		try {
			List<IclubPropertyType> results = (List<IclubPropertyType>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubPropertyType")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPropertyType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPropertyType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubPropertyType> findByPtShortDesc(Object ptShortDesc) {
		return findByProperty(PT_SHORT_DESC, ptShortDesc);
	}

	public List<IclubPropertyType> findByPtLongDesc(Object ptLongDesc) {
		return findByProperty(PT_LONG_DESC, ptLongDesc);
	}

	public List<IclubPropertyType> findByPtStatus(Object ptStatus) {
		return findByProperty(PT_STATUS, ptStatus);
	}

	public List findAll() {
		log.debug("finding all IclubPropertyType instances");
		try {
			String queryString = "from IclubPropertyType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubPropertyType merge(IclubPropertyType detachedInstance) {
		log.debug("merging IclubPropertyType instance");
		try {
			IclubPropertyType result = (IclubPropertyType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubPropertyType instance) {
		log.debug("attaching dirty IclubPropertyType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubPropertyType instance) {
		log.debug("attaching clean IclubPropertyType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getPropertyTypeBySD(String sd, Long id) {
		log.debug("Fetching all Property Type by Query :: getPropertyTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getPropertyTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Property Type", re);
			throw re;
		}
	}

	public static IclubPropertyTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubPropertyTypeDAO) ctx.getBean("IclubPropertyTypeDAO");
	}
}