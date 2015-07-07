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

import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubPropertyType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyTypeDAO;
import za.co.iclub.pss.ws.model.IclubPropertyTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPropertyTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPropertyTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPropertyTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPropertyTypeDAO iclubPropertyTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPropertyTypeModel model) {
		try {
			IclubPropertyType iPt = new IclubPropertyType();

			iPt.setPtId(iclubCommonDAO.getNextId(IclubPropertyType.class));
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPropertyTypeDAO.save(iPt);

			LOGGER.info("Save Success with ID :: " + iPt.getPtId());

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
	public ResponseModel mod(IclubPropertyTypeModel model) {
		try {
			IclubPropertyType iPt = new IclubPropertyType();

			iPt.setPtId(model.getPtId());
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPropertyTypeDAO.merge(iPt);

			LOGGER.info("Merge Success with ID :: " + model.getPtId());

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
			iclubPropertyTypeDAO.delete(iclubPropertyTypeDAO.findById(id));
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
	public <T extends IclubPropertyTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPropertyTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPropertyType iPt = (IclubPropertyType) object;

					IclubPropertyTypeModel model = new IclubPropertyTypeModel();

					model.setPtId(iPt.getPtId());
					model.setPtLongDesc(iPt.getPtLongDesc());
					model.setPtShortDesc(iPt.getPtShortDesc());
					model.setPtStatus(iPt.getPtStatus());

					if (iPt.getIclubProperties() != null && iPt.getIclubProperties().size() > 0) {
						String[] properties = new String[iPt.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty iclubProperty : iPt.getIclubProperties()) {
							properties[i] = iclubProperty.getPId();
							i++;
						}
						model.setIclubProperties(properties);
					}

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
	public IclubPropertyTypeModel getById(@PathParam("id") Long id) {
		IclubPropertyTypeModel model = new IclubPropertyTypeModel();
		try {
			IclubPropertyType bean = iclubPropertyTypeDAO.findById(id);

			model.setPtId(bean.getPtId());
			model.setPtLongDesc(bean.getPtLongDesc());
			model.setPtShortDesc(bean.getPtShortDesc());
			model.setPtStatus(bean.getPtStatus());

			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] properties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty iclubProperty : bean.getIclubProperties()) {
					properties[i] = iclubProperty.getPId();
					i++;
				}
				model.setIclubProperties(properties);
			}

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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubPropertyType.class.getSimpleName());
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

	public IclubPropertyTypeDAO getIclubPropertyTypeDAO() {
		return iclubPropertyTypeDAO;
	}

	public void setIclubPropertyTypeDAO(IclubPropertyTypeDAO iclubPropertyTypeDAO) {
		this.iclubPropertyTypeDAO = iclubPropertyTypeDAO;
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

}
