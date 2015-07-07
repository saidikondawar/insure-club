package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBuildingStateBean;
import za.co.iclub.pss.model.ws.IclubBuildingStateModel;
import za.co.iclub.pss.orm.bean.IclubBuildingState;

public class IclubBuildingStateTrans {
	
	public IclubBuildingStateBean fromWStoUI(IclubBuildingStateModel model) {
		IclubBuildingStateBean bean = new IclubBuildingStateBean();
		bean.setBsId(model.getBsId().longValue());
		bean.setBsLongDesc(model.getBsLongDesc());
		bean.setBsShortDesc(model.getBsShortDesc());
		bean.setBsStatus(model.getBsStatus());
		return bean;
	}
	
	public IclubBuildingStateModel fromUItoWS(IclubBuildingStateBean bean) {
		IclubBuildingStateModel model = new IclubBuildingStateModel();
		model.setBsId(bean.getBsId().longValue());
		model.setBsLongDesc(bean.getBsLongDesc());
		model.setBsShortDesc(bean.getBsShortDesc());
		model.setBsStatus(bean.getBsStatus());
		return model;
	}
	
	public IclubBuildingStateModel fromORMtoWS(IclubBuildingState bean) {
		IclubBuildingStateModel model = new IclubBuildingStateModel();
		model.setBsId(bean.getBsId().longValue());
		model.setBsLongDesc(bean.getBsLongDesc());
		model.setBsShortDesc(bean.getBsShortDesc());
		model.setBsStatus(bean.getBsStatus());
		return model;
	}
	
	public IclubBuildingState fromWStoORM(IclubBuildingStateModel model) {
		IclubBuildingState acctype = new IclubBuildingState();
		
		acctype.setBsId(model.getBsId());
		acctype.setBsLongDesc(model.getBsLongDesc());
		acctype.setBsShortDesc(model.getBsShortDesc());
		acctype.setBsStatus(model.getBsStatus());
		
		return acctype;
	}
}
