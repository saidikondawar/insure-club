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

import za.co.iclub.pss.model.ws.IclubPaymentStatusModel;
import za.co.iclub.pss.orm.bean.IclubPaymentStatus;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPaymentStatusDAO;
import za.co.iclub.pss.trans.IclubPaymentStatusTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPaymentStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPaymentStatusService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubPaymentStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPaymentStatusDAO iclubPaymentStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPaymentStatusModel model) {
		try {
			IclubPaymentStatus iPs = IclubPaymentStatusTrans.fromWStoORM(model);
			
			iPs.setPsId(iclubCommonDAO.getNextId(IclubPaymentStatus.class));
			
			iclubPaymentStatusDAO.save(iPs);
			
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
	public ResponseModel mod(IclubPaymentStatusModel model) {
		try {
			IclubPaymentStatus iPs = IclubPaymentStatusTrans.fromWStoORM(model);
			
			iclubPaymentStatusDAO.merge(iPs);
			
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
			iclubPaymentStatusDAO.delete(iclubPaymentStatusDAO.findById(id));
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
	public <T extends IclubPaymentStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubPaymentStatusDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPaymentStatus bean = (IclubPaymentStatus) object;
					
					IclubPaymentStatusModel model = IclubPaymentStatusTrans.fromORMtoWS(bean);
					
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
	public IclubPaymentStatusModel getById(@PathParam("id") Long id) {
		IclubPaymentStatusModel model = new IclubPaymentStatusModel();
		try {
			IclubPaymentStatus bean = iclubPaymentStatusDAO.findById(id);
			
			model = IclubPaymentStatusTrans.fromORMtoWS(bean);
			
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
			
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubPaymentStatus.class.getSimpleName());
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
	
	public IclubPaymentStatusDAO getIclubPaymentStatusDAO() {
		return iclubPaymentStatusDAO;
	}
	
	public void setIclubPaymentStatusDAO(IclubPaymentStatusDAO iclubPaymentStatusDAO) {
		this.iclubPaymentStatusDAO = iclubPaymentStatusDAO;
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
