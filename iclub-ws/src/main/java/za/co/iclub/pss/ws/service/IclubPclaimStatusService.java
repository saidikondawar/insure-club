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

import za.co.iclub.pss.orm.bean.IclubPclaimStatus;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubPclaimStatusDAO;
import za.co.iclub.pss.ws.model.IclubPclaimStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPclaimStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPclaimStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPclaimStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPclaimStatusDAO iclubPclaimStatusDAO; 

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPclaimStatusModel model) {
		try {
			IclubPclaimStatus iPs = new IclubPclaimStatus();

			iPs.setPsId(iclubCommonDAO.getNextId(IclubPclaimStatus.class));
			iPs.setPsLongDesc(model.getPsLongDesc());
			iPs.setPsShortDesc(model.getPsShortDesc());
			iPs.setPsStatus(model.getPsStatus());

			iclubPclaimStatusDAO.save(iPs);

			LOGGER.info("Save Success with ID :: " + iPs.getPsId());

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
	public ResponseModel mod(IclubPclaimStatusModel model) {
		try {
			IclubPclaimStatus iPs = new IclubPclaimStatus();

			iPs.setPsId(model.getPsId());
			iPs.setPsLongDesc(model.getPsLongDesc());
			iPs.setPsShortDesc(model.getPsShortDesc());
			iPs.setPsStatus(model.getPsStatus());

			iclubPclaimStatusDAO.merge(iPs);

			LOGGER.info("Merge Success with ID :: " + model.getPsId());

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
			iclubPclaimStatusDAO.delete(iclubPclaimStatusDAO.findById(id));
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
	public <T extends IclubPclaimStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPclaimStatusDAO.findAll();

			for (Object object : batmod) {
				IclubPclaimStatus iPs = (IclubPclaimStatus) object;

				IclubPclaimStatusModel model = new IclubPclaimStatusModel();

				model.setPsId(iPs.getPsId());
				model.setPsLongDesc(iPs.getPsLongDesc());
				model.setPsShortDesc(iPs.getPsShortDesc());
				model.setPsStatus(iPs.getPsStatus());

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
	public IclubPclaimStatusModel getById(@PathParam("id") Long id) {
		IclubPclaimStatusModel model = new IclubPclaimStatusModel();
		try {
			IclubPclaimStatus bean = iclubPclaimStatusDAO.findById(id);

			model.setPsId(bean.getPsId());
			model.setPsLongDesc(bean.getPsLongDesc());
			model.setPsShortDesc(bean.getPsShortDesc());
			model.setPsStatus(bean.getPsStatus());

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
			List data = iclubPclaimStatusDAO.getPclaimStatusBySD(val, id);
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


	 

	public IclubPclaimStatusDAO getIclubPclaimStatusDAO() {
		return iclubPclaimStatusDAO;
	}

	public void setIclubPclaimStatusDAO(IclubPclaimStatusDAO iclubPclaimStatusDAO) {
		this.iclubPclaimStatusDAO = iclubPclaimStatusDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}