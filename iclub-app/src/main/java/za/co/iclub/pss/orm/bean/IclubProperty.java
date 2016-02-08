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

/**
 * IclubProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_property", catalog = "iclubdb")
public class IclubProperty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 253597192662816479L;
	private String PId;
	private IclubOccupiedStatus iclubOccupiedStatus;
	private IclubBarType iclubBarType;
	private IclubPropertyType iclubPropertyType;
	private IclubWallType iclubWallType;
	private IclubAccessType iclubAccessType;
	private IclubPropSecType iclubPropSecType;
	private IclubRoofType iclubRoofType;
	private IclubPerson iclubPerson;
	private IclubCoverType iclubCoverType;
	private IclubPropUsageType iclubPropUsageType;
	private IclubThatchType iclubThatchType;
	private String PRegNum;
	private String PAddress;
	private Double PLat;
	private Double PLong;
	private Integer PPostalCd;
	private Integer PNoclaimYrs;
	private String PRentFurYn;
	private String PCompYn;
	private String PNorobberyYn;
	private String PSecGatesYn;
	private Double PEstValue;
	private Date PCrtdDt;
	private Double PContentCost;
	private Double PReplacementCost;
	private String PThatchType;
	private Set<IclubPropertyItem> iclubPropertyItems = new HashSet<IclubPropertyItem>(0);

	// Constructors

	/** default constructor */
	public IclubProperty() {
	}

	/** minimal constructor */
	public IclubProperty(String PId) {
		this.PId = PId;
	}

	/** full constructor */
	public IclubProperty(String PId, IclubOccupiedStatus iclubOccupiedStatus, IclubBarType iclubBarType, IclubPropertyType iclubPropertyType, IclubWallType iclubWallType, IclubAccessType iclubAccessType, IclubPropSecType iclubPropSecType, IclubRoofType iclubRoofType, IclubPerson iclubPerson, IclubCoverType iclubCoverType, IclubPropUsageType iclubPropUsageType, IclubThatchType iclubThatchType, String PRegNum, String PAddress, Double PLat, Double PLong, Integer PPostalCd, Integer PNoclaimYrs, String PRentFurYn, String PCompYn, String PNorobberyYn, String PSecGatesYn, Double PEstValue, Date PCrtdDt, Double PContentCost, Double PReplacementCost, String PThatchType, Set<IclubPropertyItem> iclubPropertyItems) {
		this.PId = PId;
		this.iclubOccupiedStatus = iclubOccupiedStatus;
		this.iclubBarType = iclubBarType;
		this.iclubPropertyType = iclubPropertyType;
		this.iclubWallType = iclubWallType;
		this.iclubAccessType = iclubAccessType;
		this.iclubPropSecType = iclubPropSecType;
		this.iclubRoofType = iclubRoofType;
		this.iclubPerson = iclubPerson;
		this.iclubCoverType = iclubCoverType;
		this.iclubPropUsageType = iclubPropUsageType;
		this.iclubThatchType = iclubThatchType;
		this.PRegNum = PRegNum;
		this.PAddress = PAddress;
		this.PLat = PLat;
		this.PLong = PLong;
		this.PPostalCd = PPostalCd;
		this.PNoclaimYrs = PNoclaimYrs;
		this.PRentFurYn = PRentFurYn;
		this.PCompYn = PCompYn;
		this.PNorobberyYn = PNorobberyYn;
		this.PSecGatesYn = PSecGatesYn;
		this.PEstValue = PEstValue;
		this.PCrtdDt = PCrtdDt;
		this.PContentCost = PContentCost;
		this.PReplacementCost = PReplacementCost;
		this.PThatchType = PThatchType;
		this.iclubPropertyItems = iclubPropertyItems;
	}

	// Property accessors
	@Id
	@Column(name = "p_id", unique = true, nullable = false, length = 36)
	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_occupied_status_id")
	public IclubOccupiedStatus getIclubOccupiedStatus() {
		return this.iclubOccupiedStatus;
	}

	public void setIclubOccupiedStatus(IclubOccupiedStatus iclubOccupiedStatus) {
		this.iclubOccupiedStatus = iclubOccupiedStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_bar_type_id")
	public IclubBarType getIclubBarType() {
		return this.iclubBarType;
	}

	public void setIclubBarType(IclubBarType iclubBarType) {
		this.iclubBarType = iclubBarType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_type_id")
	public IclubPropertyType getIclubPropertyType() {
		return this.iclubPropertyType;
	}

	public void setIclubPropertyType(IclubPropertyType iclubPropertyType) {
		this.iclubPropertyType = iclubPropertyType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_wall_type_id")
	public IclubWallType getIclubWallType() {
		return this.iclubWallType;
	}

	public void setIclubWallType(IclubWallType iclubWallType) {
		this.iclubWallType = iclubWallType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_access_type_id")
	public IclubAccessType getIclubAccessType() {
		return this.iclubAccessType;
	}

	public void setIclubAccessType(IclubAccessType iclubAccessType) {
		this.iclubAccessType = iclubAccessType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_sec_type_id")
	public IclubPropSecType getIclubPropSecType() {
		return this.iclubPropSecType;
	}

	public void setIclubPropSecType(IclubPropSecType iclubPropSecType) {
		this.iclubPropSecType = iclubPropSecType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_roof_type_id")
	public IclubRoofType getIclubRoofType() {
		return this.iclubRoofType;
	}

	public void setIclubRoofType(IclubRoofType iclubRoofType) {
		this.iclubRoofType = iclubRoofType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_cover_type_id")
	public IclubCoverType getIclubCoverType() {
		return this.iclubCoverType;
	}

	public void setIclubCoverType(IclubCoverType iclubCoverType) {
		this.iclubCoverType = iclubCoverType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_purpose_type_id")
	public IclubPropUsageType getIclubPropUsageType() {
		return this.iclubPropUsageType;
	}

	public void setIclubPropUsageType(IclubPropUsageType iclubPropUsageType) {
		this.iclubPropUsageType = iclubPropUsageType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_thatch_type_id")
	public IclubThatchType getIclubThatchType() {
		return this.iclubThatchType;
	}

	public void setIclubThatchType(IclubThatchType iclubThatchType) {
		this.iclubThatchType = iclubThatchType;
	}

	@Column(name = "p_reg_num", length = 50)
	public String getPRegNum() {
		return this.PRegNum;
	}

	public void setPRegNum(String PRegNum) {
		this.PRegNum = PRegNum;
	}

	@Column(name = "p_address", length = 999)
	public String getPAddress() {
		return this.PAddress;
	}

	public void setPAddress(String PAddress) {
		this.PAddress = PAddress;
	}

	@Column(name = "p_lat", precision = 10, scale = 7)
	public Double getPLat() {
		return this.PLat;
	}

	public void setPLat(Double PLat) {
		this.PLat = PLat;
	}

	@Column(name = "p_long", precision = 10, scale = 7)
	public Double getPLong() {
		return this.PLong;
	}

	public void setPLong(Double PLong) {
		this.PLong = PLong;
	}

	@Column(name = "p_postal_cd")
	public Integer getPPostalCd() {
		return this.PPostalCd;
	}

	public void setPPostalCd(Integer PPostalCd) {
		this.PPostalCd = PPostalCd;
	}

	@Column(name = "p_noclaim_yrs")
	public Integer getPNoclaimYrs() {
		return this.PNoclaimYrs;
	}

	public void setPNoclaimYrs(Integer PNoclaimYrs) {
		this.PNoclaimYrs = PNoclaimYrs;
	}

	@Column(name = "p_rent_fur_yn", length = 1)
	public String getPRentFurYn() {
		return this.PRentFurYn;
	}

	public void setPRentFurYn(String PRentFurYn) {
		this.PRentFurYn = PRentFurYn;
	}

	@Column(name = "p_comp_yn", length = 1)
	public String getPCompYn() {
		return this.PCompYn;
	}

	public void setPCompYn(String PCompYn) {
		this.PCompYn = PCompYn;
	}

	@Column(name = "p_norobbery_yn", length = 1)
	public String getPNorobberyYn() {
		return this.PNorobberyYn;
	}

	public void setPNorobberyYn(String PNorobberyYn) {
		this.PNorobberyYn = PNorobberyYn;
	}

	@Column(name = "p_sec_gates_yn", length = 1)
	public String getPSecGatesYn() {
		return this.PSecGatesYn;
	}

	public void setPSecGatesYn(String PSecGatesYn) {
		this.PSecGatesYn = PSecGatesYn;
	}

	@Column(name = "p_est_value", precision = 15, scale = 5)
	public Double getPEstValue() {
		return this.PEstValue;
	}

	public void setPEstValue(Double PEstValue) {
		this.PEstValue = PEstValue;
	}

	@Column(name = "p_crtd_dt", length = 19)
	public Date getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(Date PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

	@Column(name = "p_content_cost", precision = 22, scale = 0)
	public Double getPContentCost() {
		return this.PContentCost;
	}

	public void setPContentCost(Double PContentCost) {
		this.PContentCost = PContentCost;
	}

	@Column(name = "p_replacement_cost", precision = 22, scale = 0)
	public Double getPReplacementCost() {
		return this.PReplacementCost;
	}

	public void setPReplacementCost(Double PReplacementCost) {
		this.PReplacementCost = PReplacementCost;
	}

	@Column(name = "p_thatch_type", length = 1)
	public String getPThatchType() {
		return this.PThatchType;
	}

	public void setPThatchType(String PThatchType) {
		this.PThatchType = PThatchType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubProperty")
	public Set<IclubPropertyItem> getIclubPropertyItems() {
		return this.iclubPropertyItems;
	}

	public void setIclubPropertyItems(Set<IclubPropertyItem> iclubPropertyItems) {
		this.iclubPropertyItems = iclubPropertyItems;
	}

}