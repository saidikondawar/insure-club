package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubTrackerMasterBean {

	private Long tmId;
	private Long iclubPerson;
	private String tmName;
	private String tmTradeName;
	private String tmLocation;
	private Long tmLat;
	private Long tmLong;
	private String tmRegNum;
	private Timestamp tmCrtdDt;

	public Long getTmId() {
		return tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getTmName() {
		return tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	public String getTmTradeName() {
		return tmTradeName;
	}

	public void setTmTradeName(String tmTradeName) {
		this.tmTradeName = tmTradeName;
	}

	public String getTmLocation() {
		return tmLocation;
	}

	public void setTmLocation(String tmLocation) {
		this.tmLocation = tmLocation;
	}

	public Long getTmLat() {
		return tmLat;
	}

	public void setTmLat(Long tmLat) {
		this.tmLat = tmLat;
	}

	public Long getTmLong() {
		return tmLong;
	}

	public void setTmLong(Long tmLong) {
		this.tmLong = tmLong;
	}

	public String getTmRegNum() {
		return tmRegNum;
	}

	public void setTmRegNum(String tmRegNum) {
		this.tmRegNum = tmRegNum;
	}

	public Timestamp getTmCrtdDt() {
		return tmCrtdDt;
	}

	public void setTmCrtdDt(Timestamp tmCrtdDt) {
		this.tmCrtdDt = tmCrtdDt;
	}

}