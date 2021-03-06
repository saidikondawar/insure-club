package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubOccupiedStatusModel")
public class IclubOccupiedStatusModel {

	private Long osId;
	private String osShortDesc;
	private String osLongDesc;
	private String osStatus;

	public Long getOsId() {
		return osId;
	}

	public void setOsId(Long osId) {
		this.osId = osId;
	}

	public String getOsShortDesc() {
		return osShortDesc;
	}

	public void setOsShortDesc(String osShortDesc) {
		this.osShortDesc = osShortDesc;
	}

	public String getOsLongDesc() {
		return osLongDesc;
	}

	public void setOsLongDesc(String osLongDesc) {
		this.osLongDesc = osLongDesc;
	}

	public String getOsStatus() {
		return osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}

}
