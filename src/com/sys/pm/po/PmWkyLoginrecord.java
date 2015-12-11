package com.sys.pm.po;

/**
 * PmWkyLoginrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyLoginrecord implements java.io.Serializable {

	// Fields

	private String recordId;
	private String recordLoginip;
	private String recordUsernane;
	private String recordLogintime;

	// Constructors

	/** default constructor */
	public PmWkyLoginrecord() {
	}

	/** full constructor */
	public PmWkyLoginrecord(String recordId, String recordLoginip,
			String recordUsernane, String recordLogintime) {
		this.recordId = recordId;
		this.recordLoginip = recordLoginip;
		this.recordUsernane = recordUsernane;
		this.recordLogintime = recordLogintime;
	}

	// Property accessors

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordLoginip() {
		return this.recordLoginip;
	}

	public void setRecordLoginip(String recordLoginip) {
		this.recordLoginip = recordLoginip;
	}

	public String getRecordUsernane() {
		return this.recordUsernane;
	}

	public void setRecordUsernane(String recordUsernane) {
		this.recordUsernane = recordUsernane;
	}

	public String getRecordLogintime() {
		return this.recordLogintime;
	}

	public void setRecordLogintime(String recordLogintime) {
		this.recordLogintime = recordLogintime;
	}

}