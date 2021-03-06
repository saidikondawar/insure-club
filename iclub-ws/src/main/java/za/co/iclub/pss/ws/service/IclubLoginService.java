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

import za.co.iclub.pss.orm.bean.IclubLogin;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubLoginDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubRoleTypeDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityQuestionDAO;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubLoginService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubLoginService {

	protected static final Logger LOGGER = Logger.getLogger(IclubLoginService.class);
	private IclubLoginDAO iclubLoginDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubSecurityQuestionDAO iclubSecurityQuestionDAO;
	private IclubRoleTypeDAO iclubRoleTypeDAO;
	private IclubCommonDAO iclubCommonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubLoginModel model) {
		try {
			IclubLogin login = new IclubLogin();

			login.setLId(model.getLId());
			login.setLCrtdDt(model.getLCrtdDt());
			login.setLLastDate(model.getLLastDate());
			login.setLName(model.getLName());
			login.setLPasswd(model.getLPasswd());
			login.setLSecAns(model.getLSecAns());
			login.setIclubPersonByLCrtdBy(model.getIclubPersonByLCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByLCrtdBy()) : null);
			login.setIclubPersonByLPersonId(model.getIclubPersonByLPersonId() != null ? iclubPersonDAO.findById(model.getIclubPersonByLPersonId()) : null);
			login.setIclubRoleType(model.getIclubRoleType() != null ? iclubRoleTypeDAO.findById(model.getIclubRoleType()) : null);
			login.setIclubSecurityQuestion(model.getIclubSecurityQuestion() != null ? iclubSecurityQuestionDAO.findById(model.getIclubSecurityQuestion()) : null);
			login.setLProviderCd(model.getLProviderCd());
			login.setLProviderId(model.getLProviderId());
			iclubLoginDAO.save(login);

			LOGGER.info("Save Success with ID :: " + login.getLId());

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
	public ResponseModel mod(IclubLoginModel model) {
		try {
			IclubLogin login = new IclubLogin();

			login.setLId(model.getLId());
			login.setLCrtdDt(model.getLCrtdDt());
			login.setLLastDate(model.getLLastDate());
			login.setLName(model.getLName());
			login.setLPasswd(model.getLPasswd());
			login.setLSecAns(model.getLSecAns());
			login.setIclubPersonByLCrtdBy(model.getIclubPersonByLCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByLCrtdBy()) : null);
			login.setIclubPersonByLPersonId(model.getIclubPersonByLPersonId() != null ? iclubPersonDAO.findById(model.getIclubPersonByLPersonId()) : null);
			login.setIclubRoleType(model.getIclubRoleType() != null ? iclubRoleTypeDAO.findById(model.getIclubRoleType()) : null);
			login.setIclubSecurityQuestion(model.getIclubSecurityQuestion() != null ? iclubSecurityQuestionDAO.findById(model.getIclubSecurityQuestion()) : null);
			login.setLProviderCd(model.getLProviderCd());
			login.setLProviderId(model.getLProviderId());

			iclubLoginDAO.merge(login);

			LOGGER.info("Merge Success with ID :: " + model.getLId());

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
			iclubLoginDAO.delete(iclubLoginDAO.findById(id));
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
	public <T extends IclubLoginModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		try {
			List loginmod = iclubLoginDAO.findAll();
			if (loginmod != null && loginmod.size() > 0) {
				for (Object object : loginmod) {
					IclubLogin iclubLogin = (IclubLogin) object;
					IclubLoginModel ibm = new IclubLoginModel();

					ibm.setLId(iclubLogin.getLId());
					ibm.setLCrtdDt(iclubLogin.getLCrtdDt());
					ibm.setLLastDate(iclubLogin.getLLastDate());
					ibm.setLName(iclubLogin.getLName());
					ibm.setLPasswd(iclubLogin.getLPasswd());
					ibm.setLSecAns(iclubLogin.getLSecAns());
					ibm.setIclubPersonByLCrtdBy(iclubLogin.getIclubPersonByLCrtdBy() != null ? iclubLogin.getIclubPersonByLCrtdBy().getPId() : null);
					ibm.setIclubPersonByLPersonId(iclubLogin.getIclubPersonByLPersonId() != null ? iclubLogin.getIclubPersonByLPersonId().getPId() : null);
					ibm.setIclubRoleType(iclubLogin.getIclubRoleType() != null ? iclubLogin.getIclubRoleType().getRtId() : null);
					ibm.setIclubSecurityQuestion(iclubLogin.getIclubSecurityQuestion() != null ? iclubLogin.getIclubSecurityQuestion().getSqId() : null);
					ibm.setLProviderCd(iclubLogin.getLProviderCd());
					ibm.setLProviderId(iclubLogin.getLProviderId());

					ret.add((T) ibm);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/person/login/{name}/{pwd}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel login(@PathParam("name") String name, @PathParam("pwd") String pwd) {
		try {
			List logins = iclubNamedQueryDAO.verifyLogin(name, pwd);
			ResponseModel message = new ResponseModel();
			if (logins.size() == 0 || logins.get(0) == null) {
				message.setStatusCode(-1);
				message.setStatusDesc("Invalid PersonName or Password");
			} /*
			 * else if (!(((IclubLogin)
			 * logins.get(0)).getIclubPersonByLCrtdBy().
			 * getIclubEntityStatus().getEntityStatusStatus
			 * ().equalsIgnoreCase("y"))) { message.setStatusCode(1);
			 * message.setStatusDesc("Inactive Person"); }
			 */else {
				message.setStatusCode(0);
				message.setStatusDesc("Login Success");
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
	@Path("/person/{name}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubLoginModel getByPersonName(@PathParam("name") String name) {
		IclubLoginModel message = new IclubLoginModel();
		try {
			List logins = iclubLoginDAO.findByProperty("LName", name);
			if (logins != null && logins.size() > 0 && logins.get(0) != null) {
				IclubLogin login = (IclubLogin) logins.get(0);

				message.setLId(login.getLId());
				message.setLCrtdDt(login.getLCrtdDt());
				message.setLLastDate(login.getLLastDate());
				message.setLName(login.getLName());
				message.setLPasswd(login.getLPasswd());
				message.setLSecAns(login.getLSecAns());
				message.setLSecAns(login.getLSecAns());
				message.setIclubPersonByLCrtdBy(login.getIclubPersonByLCrtdBy() != null ? login.getIclubPersonByLCrtdBy().getPId() : null);
				message.setIclubPersonByLPersonId(login.getIclubPersonByLPersonId() != null ? login.getIclubPersonByLPersonId().getPId() : null);
				message.setIclubRoleType(login.getIclubRoleType() != null ? login.getIclubRoleType().getRtId() : null);
				message.setIclubSecurityQuestion(login.getIclubSecurityQuestion() != null ? login.getIclubSecurityQuestion().getSqId() : null);
				message.setLProviderCd(login.getLProviderCd());
				message.setLProviderId(login.getLProviderId());

			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/socailLogin/{userName}/{providerId}/{providerCd}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubLoginModel getByLoginIdAndProviderId(@PathParam("userName") String name, @PathParam("providerId") String providerId, @PathParam("providerCd") String providerCd) {
		IclubLoginModel bean = new IclubLoginModel();
		try {
			List logins = iclubNamedQueryDAO.getIclubLoginByIdAndProviderId(name, providerId, providerCd);
			if (logins != null && logins.size() > 0 && logins.get(0) != null) {
				IclubLogin login = (IclubLogin) logins.get(0);

				bean.setLId(login.getLId());
				bean.setLCrtdDt(login.getLCrtdDt());
				bean.setLLastDate(login.getLLastDate());
				bean.setLName(login.getLName());
				bean.setLPasswd(login.getLPasswd());
				bean.setLSecAns(login.getLSecAns());
				bean.setLSecAns(login.getLSecAns());
				bean.setIclubPersonByLCrtdBy(login.getIclubPersonByLCrtdBy() != null ? login.getIclubPersonByLCrtdBy().getPId() : null);
				bean.setIclubPersonByLPersonId(login.getIclubPersonByLPersonId() != null ? login.getIclubPersonByLPersonId().getPId() : null);
				bean.setIclubRoleType(login.getIclubRoleType() != null ? login.getIclubRoleType().getRtId() : null);
				bean.setIclubSecurityQuestion(login.getIclubSecurityQuestion() != null ? login.getIclubSecurityQuestion().getSqId() : null);
				bean.setLProviderCd(login.getLProviderCd());
				bean.setLProviderId(login.getLProviderId());
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return bean;
	}

	@GET
	@Path("/personId/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubLoginModel getByPersonId(@PathParam("id") String id) {
		IclubLoginModel message = new IclubLoginModel();
		try {
			List logins = iclubNamedQueryDAO.getIclubLoginByPersonId(id);
			if (logins != null && logins.size() > 0 && logins.get(0) != null) {
				IclubLogin login = (IclubLogin) logins.get(0);

				message.setLId(login.getLId());
				message.setLCrtdDt(login.getLCrtdDt());
				message.setLLastDate(login.getLLastDate());
				message.setLName(login.getLName());
				message.setLPasswd(login.getLPasswd());
				message.setLSecAns(login.getLSecAns());
				message.setLSecAns(login.getLSecAns());
				message.setIclubPersonByLCrtdBy(login.getIclubPersonByLCrtdBy() != null ? login.getIclubPersonByLCrtdBy().getPId() : null);
				message.setIclubPersonByLPersonId(login.getIclubPersonByLPersonId() != null ? login.getIclubPersonByLPersonId().getPId() : null);
				message.setIclubRoleType(login.getIclubRoleType() != null ? login.getIclubRoleType().getRtId() : null);
				message.setIclubSecurityQuestion(login.getIclubSecurityQuestion() != null ? login.getIclubSecurityQuestion().getSqId() : null);
				message.setLProviderCd(login.getLProviderCd());
				message.setLProviderId(login.getLProviderId());

			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return message;
	}

	public IclubLoginDAO getIclubLoginDAO() {
		return iclubLoginDAO;
	}

	public void setIclubLoginDAO(IclubLoginDAO iclubLoginDAO) {
		this.iclubLoginDAO = iclubLoginDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubSecurityQuestionDAO getIclubSecurityQuestionDAO() {
		return iclubSecurityQuestionDAO;
	}

	public void setIclubSecurityQuestionDAO(IclubSecurityQuestionDAO iclubSecurityQuestionDAO) {
		this.iclubSecurityQuestionDAO = iclubSecurityQuestionDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubRoleTypeDAO getIclubRoleTypeDAO() {
		return iclubRoleTypeDAO;
	}

	public void setIclubRoleTypeDAO(IclubRoleTypeDAO iclubRoleTypeDAO) {
		this.iclubRoleTypeDAO = iclubRoleTypeDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
