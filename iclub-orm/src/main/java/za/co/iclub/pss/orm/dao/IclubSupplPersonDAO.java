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

import za.co.iclub.pss.orm.bean.IclubSupplPerson;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSupplPerson entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSupplPerson
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSupplPersonDAO {
	private static final Logger log = Logger.getLogger(IclubSupplPersonDAO.class);
	// property constants

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

	public void save(IclubSupplPerson transientInstance) {
		log.debug("saving IclubSupplPerson instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSupplPerson persistentInstance) {
		log.debug("deleting IclubSupplPerson instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSupplPerson findById(java.lang.String id) {
		log.debug("getting IclubSupplPerson instance with id: " + id);
		try {
			IclubSupplPerson instance = (IclubSupplPerson) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSupplPerson", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSupplPerson> findByExample(IclubSupplPerson instance) {
		log.debug("finding IclubSupplPerson instance by example");
		try {
			List<IclubSupplPerson> results = (List<IclubSupplPerson>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSupplPerson").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSupplPerson instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSupplPerson as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IclubSupplPerson instances");
		try {
			String queryString = "from IclubSupplPerson";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSupplPerson merge(IclubSupplPerson detachedInstance) {
		log.debug("merging IclubSupplPerson instance");
		try {
			IclubSupplPerson result = (IclubSupplPerson) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSupplPerson instance) {
		log.debug("attaching dirty IclubSupplPerson instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSupplPerson instance) {
		log.debug("attaching clean IclubSupplPerson instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSupplPersonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSupplPersonDAO) ctx.getBean("IclubSupplPersonDAO");
	}
}