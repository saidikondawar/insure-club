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

import za.co.iclub.pss.model.ws.IclubFieldModel;
import za.co.iclub.pss.orm.bean.IclubField;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubFieldDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.trans.IclubFieldTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubFieldService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubFieldService {

	protected static final Logger LOGGER = Logger.getLogger(IclubFieldService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubFieldDAO iclubFieldDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubFieldModel model) {
		try {
			IclubField iF = IclubFieldTrans.fromWStoORM(model, iclubEntityTypeDAO);

			iF.setFId(iclubCommonDAO.getNextId(IclubField.class));

			iclubFieldDAO.save(iF);

			LOGGER.info("Save Success with ID :: " + iF.getFId());

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
	public ResponseModel mod(IclubFieldModel model) {
		try {
			IclubField iF = IclubFieldTrans.fromWStoORM(model, iclubEntityTypeDAO);

			iclubFieldDAO.merge(iF);

			LOGGER.info("Merge Success with ID :: " + model.getFId());

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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubFieldDAO.delete(iclubFieldDAO.findById(id));
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
	public <T extends IclubFieldModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubFieldDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubField bean = (IclubField) object;

					IclubFieldModel model = IclubFieldTrans.fromORMtoWS(bean);
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
	public IclubFieldModel getById(@PathParam("id") Long id) {
		IclubFieldModel model = new IclubFieldModel();
		try {
			IclubField bean = iclubFieldDAO.findById(id);

			model = IclubFieldTrans.fromORMtoWS(bean);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/getByStatus/{fieldStatus}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubFieldModel> List<T> getByFieldStatus(@PathParam("fieldStatus") String fieldStatus) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.getIclubFieldByFieldStatus(fieldStatus);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubField bean = (IclubField) object;

					IclubFieldModel model = IclubFieldTrans.fromORMtoWS(bean);
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubField.class.getSimpleName());
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

	public IclubFieldDAO getIclubFieldDAO() {
		return iclubFieldDAO;
	}

	public void setIclubFieldDAO(IclubFieldDAO iclubFieldDAO) {
		this.iclubFieldDAO = iclubFieldDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubEntityTypeDAO getIclubEntityTypeDAO() {
		return iclubEntityTypeDAO;
	}

	public void setIclubEntityTypeDAO(IclubEntityTypeDAO iclubEntityTypeDAO) {
		this.iclubEntityTypeDAO = iclubEntityTypeDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
