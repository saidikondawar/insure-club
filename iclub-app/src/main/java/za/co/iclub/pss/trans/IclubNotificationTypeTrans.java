package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubNotificationTypeBean;
import za.co.iclub.pss.model.ws.IclubNotificationTypeModel;
import za.co.iclub.pss.orm.bean.IclubNotificationType;

public class IclubNotificationTypeTrans {
	
	public IclubNotificationTypeBean fromWStoUI(IclubNotificationTypeModel model) {
		IclubNotificationTypeBean bean = new IclubNotificationTypeBean();
		bean.setNtId(model.getNtId().longValue());
		bean.setNtLongDesc(model.getNtLongDesc());
		bean.setNtShortDesc(model.getNtShortDesc());
		bean.setNtStatus(model.getNtStatus());
		return bean;
	}
	
	public IclubNotificationTypeModel fromUItoWS(IclubNotificationTypeBean bean) {
		IclubNotificationTypeModel model = new IclubNotificationTypeModel();
		model.setNtId(bean.getNtId().longValue());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setNtShortDesc(bean.getNtShortDesc());
		model.setNtStatus(bean.getNtStatus());
		return model;
	}
	
	public IclubNotificationTypeModel fromORMtoWS(IclubNotificationType bean) {
		IclubNotificationTypeModel model = new IclubNotificationTypeModel();
		model.setNtId(bean.getNtId().longValue());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setNtShortDesc(bean.getNtShortDesc());
		model.setNtStatus(bean.getNtStatus());
		return model;
	}
	
	public IclubNotificationType fromWStoORM(IclubNotificationTypeModel model) {
		IclubNotificationType acctype = new IclubNotificationType();
		
		acctype.setNtId(model.getNtId());
		acctype.setNtLongDesc(model.getNtLongDesc());
		acctype.setNtShortDesc(model.getNtShortDesc());
		acctype.setNtStatus(model.getNtStatus());
		
		return acctype;
	}
}
