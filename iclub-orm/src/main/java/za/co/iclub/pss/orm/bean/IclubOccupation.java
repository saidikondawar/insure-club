package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubOccupation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_occupation")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_cccupation where o_crtd_by=:id", name = "getOccupationByUser", resultClass = IclubOccupation.class) })
public class IclubOccupation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7363362949776623705L;
	private Long OId;
	private IclubPerson iclubPerson;
	private String ODesc;
	private String OStatus;
	private Timestamp OCrtdDt;

	// Constructors

	/** default constructor */
	public IclubOccupation() {
	}

	/** minimal constructor */
	public IclubOccupation(Long OId) {
		this.OId = OId;
	}

	/** full constructor */
	public IclubOccupation(Long OId, IclubPerson iclubPerson, String ODesc, String OStatus, Timestamp OCrtdDt) {
		this.OId = OId;
		this.iclubPerson = iclubPerson;
		this.ODesc = ODesc;
		this.OStatus = OStatus;
		this.OCrtdDt = OCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "o_id", unique = true, nullable = false)
	public Long getOId() {
		return this.OId;
	}

	public void setOId(Long OId) {
		this.OId = OId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "o_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "o_desc", length = 450)
	public String getODesc() {
		return this.ODesc;
	}

	public void setODesc(String ODesc) {
		this.ODesc = ODesc;
	}

	@Column(name = "o_status", length = 1)
	public String getOStatus() {
		return this.OStatus;
	}

	public void setOStatus(String OStatus) {
		this.OStatus = OStatus;
	}

	@Column(name = "o_crtd_dt", length = 19)
	public Timestamp getOCrtdDt() {
		return this.OCrtdDt;
	}

	public void setOCrtdDt(Timestamp OCrtdDt) {
		this.OCrtdDt = OCrtdDt;
	}

}