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

import za.co.iclub.pss.orm.bean.IclubMessage;
import za.co.iclub.pss.orm.bean.IclubMessageType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubMessageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.IclubMessageTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubMessageTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubMessageTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubMessageTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubMessageTypeDAO iclubMessageTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubMessageTypeModel model) {
		try {
			IclubMessageType iMt = new IclubMessageType();

			iMt.setMtId(iclubCommonDAO.getNextId(IclubMessageType.class));
			iMt.setMtLongDesc(model.getMtLongDesc());
			iMt.setMtShortDesc(model.getMtShortDesc());
			iMt.setMtStatus(model.getMtStatus());

			iclubMessageTypeDAO.save(iMt);

			LOGGER.info("Save Success with ID :: " + iMt.getMtId());

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
	public ResponseModel mod(IclubMessageTypeModel model) {
		try {
			IclubMessageType iMt = new IclubMessageType();

			iMt.setMtId(model.getMtId());
			iMt.setMtLongDesc(model.getMtLongDesc());
			iMt.setMtShortDesc(model.getMtShortDesc());
			iMt.setMtStatus(model.getMtStatus());

			iclubMessageTypeDAO.merge(iMt);

			LOGGER.info("Merge Success with ID :: " + model.getMtId());

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
			iclubMessageTypeDAO.delete(iclubMessageTypeDAO.findById(id));
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
	public <T extends IclubMessageTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMessageTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubMessageType iMt = (IclubMessageType) object;

					IclubMessageTypeModel model = new IclubMessageTypeModel();

					model.setMtId(iMt.getMtId());
					model.setMtLongDesc(iMt.getMtLongDesc());
					model.setMtShortDesc(iMt.getMtShortDesc());
					model.setMtStatus(iMt.getMtStatus());

					if (iMt.getIclubMessages() != null && iMt.getIclubMessages().size() > 0) {
						String[] iclubMessages = new String[iMt.getIclubMessages().size()];
						int i = 0;
						for (IclubMessage iclubMessage : iMt.getIclubMessages()) {
							iclubMessages[i] = iclubMessage.getMId();
							i++;
						}
						model.setIclubMessages(iclubMessages);
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
	public IclubMessageTypeModel getById(@PathParam("id") Long id) {
		IclubMessageTypeModel model = new IclubMessageTypeModel();
		try {
			IclubMessageType bean = iclubMessageTypeDAO.findById(id);

			model.setMtId(bean.getMtId());
			model.setMtLongDesc(bean.getMtLongDesc());
			model.setMtShortDesc(bean.getMtShortDesc());
			model.setMtStatus(bean.getMtStatus());

			if (bean.getIclubMessages() != null && bean.getIclubMessages().size() > 0) {
				String[] iclubMessages = new String[bean.getIclubMessages().size()];
				int i = 0;
				for (IclubMessage iclubMessage : bean.getIclubMessages()) {
					iclubMessages[i] = iclubMessage.getMId();
					i++;
				}
				model.setIclubMessages(iclubMessages);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubMessageType.class.getSimpleName());
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

	public IclubMessageTypeDAO getIclubMessageTypeDAO() {
		return iclubMessageTypeDAO;
	}

	public void setIclubMessageTypeDAO(IclubMessageTypeDAO iclubMessageTypeDAO) {
		this.iclubMessageTypeDAO = iclubMessageTypeDAO;
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
