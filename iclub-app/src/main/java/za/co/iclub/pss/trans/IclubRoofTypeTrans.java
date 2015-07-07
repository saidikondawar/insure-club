package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubRoofTypeBean;
import za.co.iclub.pss.model.ws.IclubRoofTypeModel;
import za.co.iclub.pss.orm.bean.IclubRoofType;

public class IclubRoofTypeTrans {
	
	public IclubRoofTypeBean fromWStoUI(IclubRoofTypeModel model) {
		IclubRoofTypeBean bean = new IclubRoofTypeBean();
		bean.setRtId(model.getRtId().longValue());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setRtShortDesc(model.getRtShortDesc());
		bean.setRtStatus(model.getRtStatus());
		return bean;
	}
	
	public IclubRoofTypeModel fromUItoWS(IclubRoofTypeBean bean) {
		IclubRoofTypeModel model = new IclubRoofTypeModel();
		model.setRtId(bean.getRtId().longValue());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}
	
	public IclubRoofTypeModel fromORMtoWS(IclubRoofType bean) {
		IclubRoofTypeModel model = new IclubRoofTypeModel();
		model.setRtId(bean.getRtId().longValue());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}
	
	public IclubRoofType fromWStoORM(IclubRoofTypeModel model) {
		IclubRoofType acctype = new IclubRoofType();
		
		acctype.setRtId(model.getRtId());
		acctype.setRtLongDesc(model.getRtLongDesc());
		acctype.setRtShortDesc(model.getRtShortDesc());
		acctype.setRtStatus(model.getRtStatus());
		
		return acctype;
	}
}
