package za.co.iclub.pss.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IclubTrackerMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_tracker_master", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "tm_name"))
public class IclubTrackerMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6613035215605375546L;
	private Long tmId;
	private IclubPerson iclubPerson;
	private String tmName;
	private String tmTradeName;
	private String tmLocation;
	private Double tmLat;
	private Double tmLong;
	private String tmRegNum;
	private Date tmCrtdDt;
	private Set<IclubSecurityDevice> iclubSecurityDevices = new HashSet<IclubSecurityDevice>(0);

	// Constructors

	/** default constructor */
	public IclubTrackerMaster() {
	}

	/** minimal constructor */
	public IclubTrackerMaster(Long tmId) {
		this.tmId = tmId;
	}

	/** full constructor */
	public IclubTrackerMaster(Long tmId, IclubPerson iclubPerson, String tmName, String tmTradeName, String tmLocation, Double tmLat, Double tmLong, String tmRegNum, Date tmCrtdDt, Set<IclubSecurityDevice> iclubSecurityDevices) {
		this.tmId = tmId;
		this.iclubPerson = iclubPerson;
		this.tmName = tmName;
		this.tmTradeName = tmTradeName;
		this.tmLocation = tmLocation;
		this.tmLat = tmLat;
		this.tmLong = tmLong;
		this.tmRegNum = tmRegNum;
		this.tmCrtdDt = tmCrtdDt;
		this.iclubSecurityDevices = iclubSecurityDevices;
	}

	// Property accessors
	@Id
	@Column(name = "tm_id", unique = true, nullable = false)
	public Long getTmId() {
		return this.tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "tm_name", unique = true, length = 250)
	public String getTmName() {
		return this.tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	@Column(name = "tm_trade_name", length = 999)
	public String getTmTradeName() {
		return this.tmTradeName;
	}

	public void setTmTradeName(String tmTradeName) {
		this.tmTradeName = tmTradeName;
	}

	@Column(name = "tm_location", length = 999)
	public String getTmLocation() {
		return this.tmLocation;
	}

	public void setTmLocation(String tmLocation) {
		this.tmLocation = tmLocation;
	}

	@Column(name = "tm_lat", precision = 10, scale = 7)
	public Double getTmLat() {
		return this.tmLat;
	}

	public void setTmLat(Double tmLat) {
		this.tmLat = tmLat;
	}

	@Column(name = "tm_long", precision = 10, scale = 7)
	public Double getTmLong() {
		return this.tmLong;
	}

	public void setTmLong(Double tmLong) {
		this.tmLong = tmLong;
	}

	@Column(name = "tm_reg_num", length = 25)
	public String getTmRegNum() {
		return this.tmRegNum;
	}

	public void setTmRegNum(String tmRegNum) {
		this.tmRegNum = tmRegNum;
	}

	@Column(name = "tm_crtd_dt", length = 19)
	public Date getTmCrtdDt() {
		return this.tmCrtdDt;
	}

	public void setTmCrtdDt(Date tmCrtdDt) {
		this.tmCrtdDt = tmCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubTrackerMaster")
	public Set<IclubSecurityDevice> getIclubSecurityDevices() {
		return this.iclubSecurityDevices;
	}

	public void setIclubSecurityDevices(Set<IclubSecurityDevice> iclubSecurityDevices) {
		this.iclubSecurityDevices = iclubSecurityDevices;
	}

}