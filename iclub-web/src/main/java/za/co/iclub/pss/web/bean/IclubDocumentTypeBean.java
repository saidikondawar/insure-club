package za.co.iclub.pss.web.bean;

public class IclubDocumentTypeBean {

	private Long dtId;
	private String dtShortDesc;
	private String dtLongDesc;
	private String dtStatus;
	private String[] iclubDocuments;

	public Long getDtId() {
		return dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	public String getDtShortDesc() {
		return dtShortDesc;
	}

	public void setDtShortDesc(String dtShortDesc) {
		this.dtShortDesc = dtShortDesc;
	}

	public String getDtLongDesc() {
		return dtLongDesc;
	}

	public void setDtLongDesc(String dtLongDesc) {
		this.dtLongDesc = dtLongDesc;
	}

	public String getDtStatus() {
		return dtStatus;
	}

	public void setDtStatus(String dtStatus) {
		this.dtStatus = dtStatus;
	}

	public String[] getIclubDocuments() {
		return iclubDocuments;
	}

	public void setIclubDocuments(String[] iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}

}
