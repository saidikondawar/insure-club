package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubSupplItem;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSupplItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSupplItem
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSupplItemDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubSupplItemDAO.class);
	// property constants
	public static final String SI_ASSESS_NUMBER = "siAssessNumber";
	public static final String SI_ITEM_ID = "siItemId";

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

	public void save(IclubSupplItem transientInstance) {
		log.debug("saving IclubSupplItem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSupplItem persistentInstance) {
		log.debug("deleting IclubSupplItem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSupplItem findById(java.lang.String id) {
		log.debug("getting IclubSupplItem instance with id: " + id);
		try {
			IclubSupplItem instance = (IclubSupplItem) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSupplItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSupplItem> findByExample(IclubSupplItem instance) {
		log.debug("finding IclubSupplItem instance by example");
		try {
			List<IclubSupplItem> results = (List<IclubSupplItem>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSupplItem").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSupplItem instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSupplItem as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSupplItem> findBySiAssessNumber(Object siAssessNumber) {
		return findByProperty(SI_ASSESS_NUMBER, siAssessNumber);
	}

	public List<IclubSupplItem> findBySiItemId(Object siItemId) {
		return findByProperty(SI_ITEM_ID, siItemId);
	}

	public List findAll() {
		log.debug("finding all IclubSupplItem instances");
		try {
			String queryString = "from IclubSupplItem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSupplItem merge(IclubSupplItem detachedInstance) {
		log.debug("merging IclubSupplItem instance");
		try {
			IclubSupplItem result = (IclubSupplItem) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSupplItem instance) {
		log.debug("attaching dirty IclubSupplItem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSupplItem instance) {
		log.debug("attaching clean IclubSupplItem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSupplItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSupplItemDAO) ctx.getBean("IclubSupplItemDAO");
	}
}