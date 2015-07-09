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
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubAccessTypeModel;
import za.co.iclub.pss.orm.bean.IclubAccessType;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.trans.IclubAccessTypeTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAccessTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAccessTypeService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubAccessTypeService.class);
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubAccessTypeModel model) {
		try {
			
			IclubAccessType acctype = IclubAccessTypeTrans.fromWStoORM(model);
			
			acctype.setAtId(iclubCommonDAO.getNextId(IclubAccessType.class));
			
			iclubAccessTypeDAO.save(acctype);
			
			LOGGER.info("Save Success with ID :: " + acctype.getAtId().longValue());
			
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
	@Transactional
	public ResponseModel mod(IclubAccessTypeModel model) {
		try {
			IclubAccessType acctype = IclubAccessTypeTrans.fromWStoORM(model);
			
			iclubAccessTypeDAO.merge(acctype);
			
			LOGGER.info("Save Success with ID :: " + model.getAtId().longValue());
			
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
	@Transactional
	public Response del(@PathParam("id") Long id) {
		try {
			iclubAccessTypeDAO.delete(iclubAccessTypeDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubAccessType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if (data != null && data.size() > 0) {
				message.setStatusCode(1);
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(0);
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(2);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubAccessTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubAccessTypeDAO.findAll();
			
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubAccessType bean = (IclubAccessType) object;
					IclubAccessTypeModel iCB = IclubAccessTypeTrans.fromORMtoWS(bean);
					
					ret.add((T) iCB);
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
	@Transactional
	public IclubAccessTypeModel getById(@PathParam("id") Long id) {
		IclubAccessTypeModel model = new IclubAccessTypeModel();
		try {
			IclubAccessType bean = iclubAccessTypeDAO.findById(id);
			
			model = IclubAccessTypeTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}
	
	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
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