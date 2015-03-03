package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubMessageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_message_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getMessageTypeBySD", query = "select * from iclub_message_type where lower(mt_short_desc) = lower(:sd) and mt_id <> :id", resultClass = IclubMessageType.class) })
public class IclubMessageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -516763458059760752L;
	private Long mtId;
	private String mtShortDesc;
	private String mtLongDesc;
	private String mtStatus;

	// Constructors

	/** default constructor */
	public IclubMessageType() {
	}

	/** minimal constructor */
	public IclubMessageType(Long mtId) {
		this.mtId = mtId;
	}

	/** full constructor */
	public IclubMessageType(Long mtId, String mtShortDesc, String mtLongDesc, String mtStatus) {
		this.mtId = mtId;
		this.mtShortDesc = mtShortDesc;
		this.mtLongDesc = mtLongDesc;
		this.mtStatus = mtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "mt_id", unique = true, nullable = false)
	public Long getMtId() {
		return this.mtId;
	}

	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}

	@Column(name = "mt_short_desc", length = 4)
	public String getMtShortDesc() {
		return this.mtShortDesc;
	}

	public void setMtShortDesc(String mtShortDesc) {
		this.mtShortDesc = mtShortDesc;
	}

	@Column(name = "mt_long_desc", length = 500)
	public String getMtLongDesc() {
		return this.mtLongDesc;
	}

	public void setMtLongDesc(String mtLongDesc) {
		this.mtLongDesc = mtLongDesc;
	}

	@Column(name = "mt_status", length = 1)
	public String getMtStatus() {
		return this.mtStatus;
	}

	public void setMtStatus(String mtStatus) {
		this.mtStatus = mtStatus;
	}

}