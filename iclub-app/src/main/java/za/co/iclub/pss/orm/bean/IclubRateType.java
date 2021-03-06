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
 * IclubRateType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_rate_type", catalog = "iclubdb")
public class IclubRateType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508100944488478623L;
	private Long rtId;
	private IclubEntityType iclubEntityType;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubField iclubField;
	private IclubPerson iclubPerson;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;
	private String rtType;
	private String rtQuoteType;
	private Date rtCrtdDt;
	private Set<IclubRateEngine> iclubRateEngines = new HashSet<IclubRateEngine>(0);

	// Constructors

	/** default constructor */
	public IclubRateType() {
	}

	/** minimal constructor */
	public IclubRateType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRateType(Long rtId, IclubEntityType iclubEntityType, IclubInsuranceItemType iclubInsuranceItemType, IclubField iclubField, IclubPerson iclubPerson, String rtShortDesc, String rtLongDesc, String rtStatus, String rtType, String rtQuoteType, Date rtCrtdDt, Set<IclubRateEngine> iclubRateEngines) {
		this.rtId = rtId;
		this.iclubEntityType = iclubEntityType;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubField = iclubField;
		this.iclubPerson = iclubPerson;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtStatus = rtStatus;
		this.rtType = rtType;
		this.rtQuoteType = rtQuoteType;
		this.rtCrtdDt = rtCrtdDt;
		this.iclubRateEngines = iclubRateEngines;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_entity_type_id")
	public IclubEntityType getIclubEntityType() {
		return this.iclubEntityType;
	}

	public void setIclubEntityType(IclubEntityType iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_field_id")
	public IclubField getIclubField() {
		return this.iclubField;
	}

	public void setIclubField(IclubField iclubField) {
		this.iclubField = iclubField;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	@Column(name = "rt_type", length = 1)
	public String getRtType() {
		return this.rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	@Column(name = "rt_quote_type", length = 1)
	public String getRtQuoteType() {
		return this.rtQuoteType;
	}

	public void setRtQuoteType(String rtQuoteType) {
		this.rtQuoteType = rtQuoteType;
	}

	@Column(name = "rt_crtd_dt", length = 19)
	public Date getRtCrtdDt() {
		return this.rtCrtdDt;
	}

	public void setRtCrtdDt(Date rtCrtdDt) {
		this.rtCrtdDt = rtCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubRateType")
	public Set<IclubRateEngine> getIclubRateEngines() {
		return this.iclubRateEngines;
	}

	public void setIclubRateEngines(Set<IclubRateEngine> iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}

}