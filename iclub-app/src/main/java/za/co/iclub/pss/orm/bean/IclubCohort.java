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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubCohort entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort", catalog = "iclubdb")
public class IclubCohort implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7156134120479766556L;
	private String CId;
	private IclubPerson iclubPersonByCAdminId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPersonByCPrimaryUserId;
	private IclubCohortType iclubCohortType;
	private IclubPerson iclubPersonByCCrtdBy;
	private String CName;
	private String CEmail;
	private Date CInitDt;
	private Date CFinalizeDt;
	private Double CTotalContrib;
	private Double CCollectedContrib;
	private Integer CCurMemberCnt;
	private Date CCrtdDt;
	private Set<IclubCohortPerson> iclubCohortPersons = new HashSet<IclubCohortPerson>(0);
	private Set<IclubCohortClaim> iclubCohortClaims = new HashSet<IclubCohortClaim>(0);
	private Set<IclubCohortInvite> iclubCohortInvites = new HashSet<IclubCohortInvite>(0);
	private Set<IclubCohortActivity> iclubCohortActivities = new HashSet<IclubCohortActivity>(0);
	private Set<IclubPerson> iclubPersons = new HashSet<IclubPerson>(0);

	// Constructors

	/** default constructor */
	public IclubCohort() {
	}

	/** minimal constructor */
	public IclubCohort(String CId) {
		this.CId = CId;
	}

	/** full constructor */
	public IclubCohort(String CId, IclubPerson iclubPersonByCAdminId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPersonByCPrimaryUserId, IclubCohortType iclubCohortType, IclubPerson iclubPersonByCCrtdBy, String CName, String CEmail, Date CInitDt, Date CFinalizeDt, Double CTotalContrib, Double CCollectedContrib, Integer CCurMemberCnt, Date CCrtdDt, Set<IclubCohortPerson> iclubCohortPersons, Set<IclubCohortClaim> iclubCohortClaims, Set<IclubCohortInvite> iclubCohortInvites, Set<IclubCohortActivity> iclubCohortActivities, Set<IclubPerson> iclubPersons) {
		this.CId = CId;
		this.iclubPersonByCAdminId = iclubPersonByCAdminId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPersonByCPrimaryUserId = iclubPersonByCPrimaryUserId;
		this.iclubCohortType = iclubCohortType;
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
		this.CName = CName;
		this.CEmail = CEmail;
		this.CInitDt = CInitDt;
		this.CFinalizeDt = CFinalizeDt;
		this.CTotalContrib = CTotalContrib;
		this.CCollectedContrib = CCollectedContrib;
		this.CCurMemberCnt = CCurMemberCnt;
		this.CCrtdDt = CCrtdDt;
		this.iclubCohortPersons = iclubCohortPersons;
		this.iclubCohortClaims = iclubCohortClaims;
		this.iclubCohortInvites = iclubCohortInvites;
		this.iclubCohortActivities = iclubCohortActivities;
		this.iclubPersons = iclubPersons;
	}

	// Property accessors
	@Id
	@Column(name = "c_id", unique = true, nullable = false, length = 36)
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_admin_id")
	public IclubPerson getIclubPersonByCAdminId() {
		return this.iclubPersonByCAdminId;
	}

	public void setIclubPersonByCAdminId(IclubPerson iclubPersonByCAdminId) {
		this.iclubPersonByCAdminId = iclubPersonByCAdminId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_insurance_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_primary_user_id")
	public IclubPerson getIclubPersonByCPrimaryUserId() {
		return this.iclubPersonByCPrimaryUserId;
	}

	public void setIclubPersonByCPrimaryUserId(IclubPerson iclubPersonByCPrimaryUserId) {
		this.iclubPersonByCPrimaryUserId = iclubPersonByCPrimaryUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_cohort_type_id")
	public IclubCohortType getIclubCohortType() {
		return this.iclubCohortType;
	}

	public void setIclubCohortType(IclubCohortType iclubCohortType) {
		this.iclubCohortType = iclubCohortType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_crtd_by")
	public IclubPerson getIclubPersonByCCrtdBy() {
		return this.iclubPersonByCCrtdBy;
	}

	public void setIclubPersonByCCrtdBy(IclubPerson iclubPersonByCCrtdBy) {
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
	}

	@Column(name = "c_name", length = 999)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_email", length = 999)
	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_init_dt", length = 10)
	public Date getCInitDt() {
		return this.CInitDt;
	}

	public void setCInitDt(Date CInitDt) {
		this.CInitDt = CInitDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_finalize_dt", length = 10)
	public Date getCFinalizeDt() {
		return this.CFinalizeDt;
	}

	public void setCFinalizeDt(Date CFinalizeDt) {
		this.CFinalizeDt = CFinalizeDt;
	}

	@Column(name = "c_total_contrib", precision = 20, scale = 5)
	public Double getCTotalContrib() {
		return this.CTotalContrib;
	}

	public void setCTotalContrib(Double CTotalContrib) {
		this.CTotalContrib = CTotalContrib;
	}

	@Column(name = "c_collected_contrib", precision = 20, scale = 5)
	public Double getCCollectedContrib() {
		return this.CCollectedContrib;
	}

	public void setCCollectedContrib(Double CCollectedContrib) {
		this.CCollectedContrib = CCollectedContrib;
	}

	@Column(name = "c_cur_member_cnt")
	public Integer getCCurMemberCnt() {
		return this.CCurMemberCnt;
	}

	public void setCCurMemberCnt(Integer CCurMemberCnt) {
		this.CCurMemberCnt = CCurMemberCnt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_crtd_dt", length = 10)
	public Date getCCrtdDt() {
		return this.CCrtdDt;
	}

	public void setCCrtdDt(Date CCrtdDt) {
		this.CCrtdDt = CCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortPerson> getIclubCohortPersons() {
		return this.iclubCohortPersons;
	}

	public void setIclubCohortPersons(Set<IclubCohortPerson> iclubCohortPersons) {
		this.iclubCohortPersons = iclubCohortPersons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortClaim> getIclubCohortClaims() {
		return this.iclubCohortClaims;
	}

	public void setIclubCohortClaims(Set<IclubCohortClaim> iclubCohortClaims) {
		this.iclubCohortClaims = iclubCohortClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortInvite> getIclubCohortInvites() {
		return this.iclubCohortInvites;
	}

	public void setIclubCohortInvites(Set<IclubCohortInvite> iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortActivity> getIclubCohortActivities() {
		return this.iclubCohortActivities;
	}

	public void setIclubCohortActivities(Set<IclubCohortActivity> iclubCohortActivities) {
		this.iclubCohortActivities = iclubCohortActivities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubPerson> getIclubPersons() {
		return this.iclubPersons;
	}

	public void setIclubPersons(Set<IclubPerson> iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

}