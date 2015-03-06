package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubCoverTypeBean {

	private Long ctId;
	private String iclubPerson;
	private String iclubInsuranceItemType;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	private Timestamp ctCrtdDt;

	public Long getCtId() {
		return ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(String iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getCtShortDesc() {
		return ctShortDesc;
	}

	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}

	public String getCtLongDesc() {
		return ctLongDesc;
	}

	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}

	public String getCtStatus() {
		return ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

	public Timestamp getCtCrtdDt() {
		return ctCrtdDt;
	}

	public void setCtCrtdDt(Timestamp ctCrtdDt) {
		this.ctCrtdDt = ctCrtdDt;
	}
}
