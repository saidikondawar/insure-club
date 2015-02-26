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

import za.co.iclub.pss.orm.bean.IclubIdType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubIdTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubIdTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubIdTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubIdTypeDAO iclubIdTypeDAO; 

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubIdTypeModel model) {
		try {
			IclubIdType iIt = new IclubIdType();

			iIt.setItId(iclubCommonDAO.getNextId(IclubIdType.class));
			iIt.setItLongDesc(model.getItLongDesc());
			iIt.setItShortDesc(model.getItShortDesc());
			iIt.setItStatus(model.getItStatus());

			iclubIdTypeDAO.save(iIt);

			LOGGER.info("Save Success with ID :: " + iIt.getItId());

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
	public ResponseModel mod(IclubIdTypeModel model) {
		try {
			IclubIdType iIt = new IclubIdType();

			iIt.setItId(model.getItId());
			iIt.setItLongDesc(model.getItLongDesc());
			iIt.setItShortDesc(model.getItShortDesc());
			iIt.setItStatus(model.getItStatus());

			iclubIdTypeDAO.merge(iIt);

			LOGGER.info("Merge Success with ID :: " + model.getItId());

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
			iclubIdTypeDAO.delete(iclubIdTypeDAO.findById(id));
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
	public <T extends IclubIdTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubIdTypeDAO.findAll();

			for (Object object : batmod) {
				IclubIdType iIt = (IclubIdType) object;

				IclubIdTypeModel model = new IclubIdTypeModel();

				model.setItId(iIt.getItId());
				model.setItLongDesc(iIt.getItLongDesc());
				model.setItShortDesc(iIt.getItShortDesc());
				model.setItStatus(iIt.getItStatus());

				ret.add((T) model);
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
	public IclubIdTypeModel getById(@PathParam("id") Long id) {
		IclubIdTypeModel model = new IclubIdTypeModel();
		try {
			IclubIdType bean = iclubIdTypeDAO.findById(id);

			model.setItId(bean.getItId());
			model.setItLongDesc(bean.getItLongDesc());
			model.setItShortDesc(bean.getItShortDesc());
			model.setItStatus(bean.getItStatus());

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
			List data = iclubIdTypeDAO.getIdTypeBySD(val, id);
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


	 

	public IclubIdTypeDAO getIclubIdTypeDAO() {
		return iclubIdTypeDAO;
	}

	public void setIclubIdTypeDAO(IclubIdTypeDAO iclubIdTypeDAO) {
		this.iclubIdTypeDAO = iclubIdTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}