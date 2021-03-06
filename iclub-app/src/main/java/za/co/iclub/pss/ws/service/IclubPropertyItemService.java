package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubPropertyItemModel;
import za.co.iclub.pss.orm.bean.IclubPropertyItem;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyItemDAO;
import za.co.iclub.pss.trans.IclubPropertyItemTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPropertyItemService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPropertyItemService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPropertyItemService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPropertyItemDAO iclubPropertyItemDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubPropertyDAO iclubPropertyDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPropertyItemModel model) {
		try {
			IclubPropertyItem iTt = IclubPropertyItemTrans.fromWStoORM(model, iclubPersonDAO, iclubPropertyDAO);

			iclubPropertyItemDAO.save(iTt);

			LOGGER.info("Save Success with ID :: " + iTt.getPiId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPropertyItemModel model) {
		try {
			IclubPropertyItem iTt = IclubPropertyItemTrans.fromWStoORM(model, iclubPersonDAO, iclubPropertyDAO);

			iclubPropertyItemDAO.merge(iTt);

			LOGGER.info("Merge Success with ID :: " + model.getPiId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@GET
	@Path("/del/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPropertyItemDAO.delete(iclubPropertyItemDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/delByProperty/{propertyId}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response delByProperty(@PathParam("propertyId") String propertyId) {
		try {
			List batmod = iclubNamedQueryDAO.getPropertyItemByProperty(propertyId);
			for (Object object : batmod) {
				IclubPropertyItem iTt = (IclubPropertyItem) object;
				iclubPropertyItemDAO.delete(iTt);
			}
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPropertyItemModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPropertyItemDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropertyItem bean = (IclubPropertyItem) object;

					IclubPropertyItemModel model = IclubPropertyItemTrans.fromORMtoWS(bean);

					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/listByProperty/{propertyId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPropertyItemModel> List<T> listByProperty(@PathParam("propertyId") String propertyId) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.getIclubPropertyItemByProperty(propertyId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropertyItem bean = (IclubPropertyItem) object;

					IclubPropertyItemModel model = IclubPropertyItemTrans.fromORMtoWS(bean);

					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPropertyItemModel getById(@PathParam("id") String id) {
		IclubPropertyItemModel model = new IclubPropertyItemModel();
		try {
			IclubPropertyItem bean = iclubPropertyItemDAO.findById(id);

			model = IclubPropertyItemTrans.fromORMtoWS(bean);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubPropertyItem.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	public IclubPropertyItemDAO getIclubPropertyItemDAO() {
		return iclubPropertyItemDAO;
	}

	public void setIclubPropertyItemDAO(IclubPropertyItemDAO iclubPropertyItemDAO) {
		this.iclubPropertyItemDAO = iclubPropertyItemDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubPropertyDAO getIclubPropertyDAO() {
		return iclubPropertyDAO;
	}

	public void setIclubPropertyDAO(IclubPropertyDAO iclubPropertyDAO) {
		this.iclubPropertyDAO = iclubPropertyDAO;
	}
}
