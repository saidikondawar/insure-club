package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubSecurityMasterBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubSecurityMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubSecurityMasterController")
@SessionScoped
public class IclubSecurityMasterController implements Serializable {

 
	private static final long serialVersionUID = 2577696720206392227L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubSecurityMasterController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityMasterService/";
	private List<IclubSecurityMasterBean> beans;
	private List<IclubSecurityMasterBean> dashBoardBeans;
	private IclubSecurityMasterBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;

	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();

	}

	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		viewParam = 1l;
	}

	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubSecurityMasterBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		viewParam = 1l;
	}

	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		viewParam = 2l;
	}

	public List<IclubSecurityMasterBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubSecurityMasterModel> models = new ArrayList<IclubSecurityMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityMasterModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubSecurityMasterBean>();
		for (IclubSecurityMasterModel model : models) {
			IclubSecurityMasterBean bean = new IclubSecurityMasterBean();

			bean.setSmId(model.getSmId());
			bean.setSmCrtdDt(model.getSmCrtdDt());
			bean.setSmDesc(model.getSmDesc());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson() );
			bean.setSmStatus(model.getSmStatus());
			 

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubSecurityMasterBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubSecurityMasterBean();
	}

	public void addIclubSecurityMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubSecurityMaster");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubSecurityMasterModel model = new IclubSecurityMasterModel();

				
				model.setSmId(UUID.randomUUID().toString());
				model.setSmDesc(bean.getSmDesc());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setSmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setSmStatus(bean.getSmStatus());
				 

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubSecurityMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubSecurityMaster");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubSecurityMasterModel model = new IclubSecurityMasterModel();

				model.setSmId(bean.getSmId());
				model.setSmDesc(bean.getSmDesc());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setSmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setSmStatus(bean.getSmStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubSecurityMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubSecurityMaster");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getSmId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("securitymaster") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubSecurityMasterBean getBean() {
		if (bean == null)
			bean = new IclubSecurityMasterBean();
		return bean;
	}

	public void setBean(IclubSecurityMasterBean bean) {
		this.bean = bean;
	}

	public boolean isShowCreateCont() {
		return showCreateCont;
	}

	public void setShowCreateCont(boolean showCreateCont) {
		this.showCreateCont = showCreateCont;
	}

	public boolean isShowViewCont() {
		return showViewCont;
	}

	public void setShowViewCont(boolean showViewCont) {
		this.showViewCont = showViewCont;
	}

	public boolean isShowEditCont() {
		return showEditCont;
	}

	public void setShowEditCont(boolean showEditCont) {
		this.showEditCont = showEditCont;
	}

	public Long getViewParam() {
		return viewParam;
	}

	public void setViewParam(Long viewParam) {
		this.viewParam = viewParam;
	}

	public String getSessionUserId() {
		sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public String getUserName() {
		userName = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.scname")).toString();
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ResourceBundle getLabelBundle() {
		if (labelBundle == null) {
			labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		}
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	public List<IclubSecurityMasterBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubSecurityMasterModel> models = new ArrayList<IclubSecurityMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityMasterModel.class));
		client.close();
		beans = new ArrayList<IclubSecurityMasterBean>();
		for (IclubSecurityMasterModel model : models) {

			IclubSecurityMasterBean bean = new IclubSecurityMasterBean();
			
			bean.setSmId(model.getSmId());
			bean.setSmCrtdDt(model.getSmCrtdDt());
			bean.setSmDesc(model.getSmDesc());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson() );
			bean.setSmStatus(model.getSmStatus());

			 

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubSecurityMasterBean> beans) {
		this.beans = beans;
	}

}