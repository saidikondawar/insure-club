package za.co.iclub.pss.web.bean;


public class IclubSecurityQuestionBean {

	private Long sqId;
	private String sqShortDesc;
	private String sqLongDesc;
	private String sqStatus;
	private Long[] iclubLogins;

	public Long getSqId() {
		return sqId;
	}

	public void setSqId(Long sqId) {
		this.sqId = sqId;
	}

	public String getSqShortDesc() {
		return sqShortDesc;
	}

	public void setSqShortDesc(String sqShortDesc) {
		this.sqShortDesc = sqShortDesc;
	}

	public String getSqLongDesc() {
		return sqLongDesc;
	}

	public void setSqLongDesc(String sqLongDesc) {
		this.sqLongDesc = sqLongDesc;
	}

	public String getSqStatus() {
		return sqStatus;
	}

	public void setSqStatus(String sqStatus) {
		this.sqStatus = sqStatus;
	}

	public Long[] getIclubLogins() {
		return iclubLogins;
	}

	public void setIclubLogins(Long[] iclubLogins) {
		this.iclubLogins = iclubLogins;
	}
}
