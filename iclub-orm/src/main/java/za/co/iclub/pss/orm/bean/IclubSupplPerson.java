package za.co.iclub.pss.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubSupplPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_suppl_person")
public class IclubSupplPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6189391425794696141L;
	private String spId;
	private IclubPerson iclubPersonBySpCrtdBy;
	private IclubPerson iclubPersonBySpPersonId;
	private IclubSupplMaster iclubSupplMaster;
	private Date spCrtdDt;

	// Constructors

	/** default constructor */
	public IclubSupplPerson() {
	}

	/** minimal constructor */
	public IclubSupplPerson(String spId) {
		this.spId = spId;
	}

	/** full constructor */
	public IclubSupplPerson(String spId, IclubPerson iclubPersonBySpCrtdBy, IclubPerson iclubPersonBySpPersonId, IclubSupplMaster iclubSupplMaster, Date spCrtdDt) {
		this.spId = spId;
		this.iclubPersonBySpCrtdBy = iclubPersonBySpCrtdBy;
		this.iclubPersonBySpPersonId = iclubPersonBySpPersonId;
		this.iclubSupplMaster = iclubSupplMaster;
		this.spCrtdDt = spCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "sp_id", unique = true, nullable = false, length = 36)
	public String getSpId() {
		return this.spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sp_crtd_by")
	public IclubPerson getIclubPersonBySpCrtdBy() {
		return this.iclubPersonBySpCrtdBy;
	}

	public void setIclubPersonBySpCrtdBy(IclubPerson iclubPersonBySpCrtdBy) {
		this.iclubPersonBySpCrtdBy = iclubPersonBySpCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sp_person_id")
	public IclubPerson getIclubPersonBySpPersonId() {
		return this.iclubPersonBySpPersonId;
	}

	public void setIclubPersonBySpPersonId(IclubPerson iclubPersonBySpPersonId) {
		this.iclubPersonBySpPersonId = iclubPersonBySpPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sp_suppl_id")
	public IclubSupplMaster getIclubSupplMaster() {
		return this.iclubSupplMaster;
	}

	public void setIclubSupplMaster(IclubSupplMaster iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
	}

	@Column(name = "sp_crtd_dt", length = 19)
	public Date getSpCrtdDt() {
		return this.spCrtdDt;
	}

	public void setSpCrtdDt(Date spCrtdDt) {
		this.spCrtdDt = spCrtdDt;
	}

}