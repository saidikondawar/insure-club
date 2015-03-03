package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubDocumentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_document_type", catalog = "iclubdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getDocumentTypeBySD", query = "select * from iclub_document_type where lower(dt_short_desc) = lower(:sd) and dt_id <> :id", resultClass = IclubDocumentType.class) })
public class IclubDocumentType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880948111887949428L;
	private Long dtId;
	private String dtShortDesc;
	private String dtLongDesc;
	private String dtStatus;

	// Constructors

	/** default constructor */
	public IclubDocumentType() {
	}

	/** minimal constructor */
	public IclubDocumentType(Long dtId) {
		this.dtId = dtId;
	}

	/** full constructor */
	public IclubDocumentType(Long dtId, String dtShortDesc, String dtLongDesc, String dtStatus) {
		this.dtId = dtId;
		this.dtShortDesc = dtShortDesc;
		this.dtLongDesc = dtLongDesc;
		this.dtStatus = dtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "dt_id", unique = true, nullable = false)
	public Long getDtId() {
		return this.dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	@Column(name = "dt_short_desc", length = 4)
	public String getDtShortDesc() {
		return this.dtShortDesc;
	}

	public void setDtShortDesc(String dtShortDesc) {
		this.dtShortDesc = dtShortDesc;
	}

	@Column(name = "dt_long_desc", length = 500)
	public String getDtLongDesc() {
		return this.dtLongDesc;
	}

	public void setDtLongDesc(String dtLongDesc) {
		this.dtLongDesc = dtLongDesc;
	}

	@Column(name = "dt_status", length = 1)
	public String getDtStatus() {
		return this.dtStatus;
	}

	public void setDtStatus(String dtStatus) {
		this.dtStatus = dtStatus;
	}

}