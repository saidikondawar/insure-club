package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubVehUsageTypeBean;
import za.co.iclub.pss.model.ws.IclubVehUsageTypeModel;
import za.co.iclub.pss.orm.bean.IclubVehUsageType;

public class IclubVehUsageTypeTrans {
	public IclubVehUsageTypeBean fromWStoUI(IclubVehUsageTypeModel model) {
		IclubVehUsageTypeBean bean = new IclubVehUsageTypeBean();
		bean.setVutId(model.getVutId().longValue());
		bean.setVutLongDesc(model.getVutLongDesc());
		bean.setVutShortDesc(model.getVutShortDesc());
		bean.setVutStatus(model.getVutStatus());
		return bean;
	}
	
	public IclubVehUsageTypeModel fromUItoWS(IclubVehUsageTypeBean bean) {
		IclubVehUsageTypeModel model = new IclubVehUsageTypeModel();
		model.setVutId(bean.getVutId().longValue());
		model.setVutLongDesc(bean.getVutLongDesc());
		model.setVutShortDesc(bean.getVutShortDesc());
		model.setVutStatus(bean.getVutStatus());
		return model;
	}
	
	public IclubVehUsageTypeModel fromORMtoWS(IclubVehUsageType bean) {
		IclubVehUsageTypeModel model = new IclubVehUsageTypeModel();
		model.setVutId(bean.getVutId().longValue());
		model.setVutLongDesc(bean.getVutLongDesc());
		model.setVutShortDesc(bean.getVutShortDesc());
		model.setVutStatus(bean.getVutStatus());
		return model;
	}
	
	public IclubVehUsageType fromWStoORM(IclubVehUsageTypeModel model) {
		IclubVehUsageType acctype = new IclubVehUsageType();
		
		acctype.setVutId(model.getVutId());
		acctype.setVutLongDesc(model.getVutLongDesc());
		acctype.setVutShortDesc(model.getVutShortDesc());
		acctype.setVutStatus(model.getVutStatus());
		
		return acctype;
	}
}
