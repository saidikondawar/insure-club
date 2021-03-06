package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubClaimItemModel")
public class IclubClaimItemModel {

	private String ciId;
	private String iclubSupplMasterByCiHandlerId;
	private String iclubInsuranceItem;
	private String iclubSupplMasterByCiAssesorId;
	private String iclubClaim;
	private Long iclubClaimStatus;
	private Double ciValue;
	private String ciCrtdBy;
	private Date ciCrtdDt;

	public String getCiId() {
		return ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	public String getIclubSupplMasterByCiHandlerId() {
		return iclubSupplMasterByCiHandlerId;
	}

	public void setIclubSupplMasterByCiHandlerId(String iclubSupplMasterByCiHandlerId) {
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
	}

	public String getIclubInsuranceItem() {
		return iclubInsuranceItem;
	}

	public void setIclubInsuranceItem(String iclubInsuranceItem) {
		this.iclubInsuranceItem = iclubInsuranceItem;
	}

	public String getIclubSupplMasterByCiAssesorId() {
		return iclubSupplMasterByCiAssesorId;
	}

	public void setIclubSupplMasterByCiAssesorId(String iclubSupplMasterByCiAssesorId) {
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
	}

	public String getIclubClaim() {
		return iclubClaim;
	}

	public void setIclubClaim(String iclubClaim) {
		this.iclubClaim = iclubClaim;
	}

	public Long getIclubClaimStatus() {
		return iclubClaimStatus;
	}

	public void setIclubClaimStatus(Long iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	public Double getCiValue() {
		return ciValue;
	}

	public void setCiValue(Double ciValue) {
		this.ciValue = ciValue;
	}

	public String getCiCrtdBy() {
		return ciCrtdBy;
	}

	public void setCiCrtdBy(String ciCrtdBy) {
		this.ciCrtdBy = ciCrtdBy;
	}

	public Date getCiCrtdDt() {
		return ciCrtdDt;
	}

	public void setCiCrtdDt(Date ciCrtdDt) {
		this.ciCrtdDt = ciCrtdDt;
	}

}
