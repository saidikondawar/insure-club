package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubDriverModel")
public class IclubDriverModel {
	
	private String DId;
	private Long iclubAccessTypeByDAccessTypeId;
	private Long iclubLicenseCode;
	private String iclubPersonByDCrtdBy;
	private Long iclubMaritialStatus;
	private String iclubPersonByDPersonId;
	private String DName;
	private String DLicenseNum;
	private Date DIssueDt;
	private Date DDob;
	private Date DCrtdDt;
	private String[] iclubVehicles;
	
	public String getDId() {
		return DId;
	}
	
	public void setDId(String dId) {
		DId = dId;
	}
	
	public Long getIclubLicenseCode() {
		return iclubLicenseCode;
	}
	
	public void setIclubLicenseCode(Long iclubLicenseCode) {
		this.iclubLicenseCode = iclubLicenseCode;
	}
	
	public String getIclubPersonByDCrtdBy() {
		return iclubPersonByDCrtdBy;
	}
	
	public void setIclubPersonByDCrtdBy(String iclubPersonByDCrtdBy) {
		this.iclubPersonByDCrtdBy = iclubPersonByDCrtdBy;
	}
	
	public Long getIclubMaritialStatus() {
		return iclubMaritialStatus;
	}
	
	public void setIclubMaritialStatus(Long iclubMaritialStatus) {
		this.iclubMaritialStatus = iclubMaritialStatus;
	}
	
	public String getIclubPersonByDPersonId() {
		return iclubPersonByDPersonId;
	}
	
	public void setIclubPersonByDPersonId(String iclubPersonByDPersonId) {
		this.iclubPersonByDPersonId = iclubPersonByDPersonId;
	}
	
	public String getDName() {
		return DName;
	}
	
	public void setDName(String dName) {
		DName = dName;
	}
	
	public String getDLicenseNum() {
		return DLicenseNum;
	}
	
	public void setDLicenseNum(String dLicenseNum) {
		DLicenseNum = dLicenseNum;
	}
	
	public Date getDIssueDt() {
		return DIssueDt;
	}
	
	public void setDIssueDt(Date dIssueDt) {
		DIssueDt = dIssueDt;
	}
	
	public Date getDDob() {
		return DDob;
	}
	
	public void setDDob(Date dDob) {
		DDob = dDob;
	}
	
	public Date getDCrtdDt() {
		return DCrtdDt;
	}
	
	public void setDCrtdDt(Date dCrtdDt) {
		DCrtdDt = dCrtdDt;
	}
	
	public String[] getIclubVehicles() {
		return iclubVehicles;
	}
	
	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}
	
	public Long getIclubAccessTypeByDAccessTypeId() {
		return iclubAccessTypeByDAccessTypeId;
	}
	
	public void setIclubAccessTypeByDAccessTypeId(Long iclubAccessTypeByDAccessTypeId) {
		this.iclubAccessTypeByDAccessTypeId = iclubAccessTypeByDAccessTypeId;
	}
}
