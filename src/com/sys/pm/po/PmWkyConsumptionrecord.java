package com.sys.pm.po;

/**
 * PmWkyConsumptionrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyConsumptionrecord implements java.io.Serializable {

	// Fields

	private String recordId;
	private String recordDeposittype;
	private String recordType;
	private String recordConsumptiontype;
	private Double recordConsumptioncredit;
	private String recordRemarks;
	private String recordUsernane;
	private String recordTime;

	// Constructors

	/** default constructor */
	public PmWkyConsumptionrecord() {
	}

	/** minimal constructor */
	public PmWkyConsumptionrecord(String recordId, String recordDeposittype,
			String recordType, String recordConsumptiontype,
			Double recordConsumptioncredit, String recordUsernane,
			String recordTime) {
		this.recordId = recordId;
		this.recordDeposittype = recordDeposittype;
		this.recordType = recordType;
		this.recordConsumptiontype = recordConsumptiontype;
		this.recordConsumptioncredit = recordConsumptioncredit;
		this.recordUsernane = recordUsernane;
		this.recordTime = recordTime;
	}

	/** full constructor */
	public PmWkyConsumptionrecord(String recordId, String recordDeposittype,
			String recordType, String recordConsumptiontype,
			Double recordConsumptioncredit, String recordRemarks,
			String recordUsernane, String recordTime) {
		this.recordId = recordId;
		this.recordDeposittype = recordDeposittype;
		this.recordType = recordType;
		this.recordConsumptiontype = recordConsumptiontype;
		this.recordConsumptioncredit = recordConsumptioncredit;
		this.recordRemarks = recordRemarks;
		this.recordUsernane = recordUsernane;
		this.recordTime = recordTime;
	}

	// Property accessors

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordDeposittype() {
		return this.recordDeposittype;
	}

	public void setRecordDeposittype(String recordDeposittype) {
		this.recordDeposittype = recordDeposittype;
	}

	public String getRecordType() {
		return this.recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordConsumptiontype() {
		return this.recordConsumptiontype;
	}

	public void setRecordConsumptiontype(String recordConsumptiontype) {
		this.recordConsumptiontype = recordConsumptiontype;
	}

	public Double getRecordConsumptioncredit() {
		return this.recordConsumptioncredit;
	}

	public void setRecordConsumptioncredit(Double recordConsumptioncredit) {
		this.recordConsumptioncredit = recordConsumptioncredit;
	}

	public String getRecordRemarks() {
		return this.recordRemarks;
	}

	public void setRecordRemarks(String recordRemarks) {
		this.recordRemarks = recordRemarks;
	}

	public String getRecordUsernane() {
		return this.recordUsernane;
	}

	public void setRecordUsernane(String recordUsernane) {
		this.recordUsernane = recordUsernane;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

}