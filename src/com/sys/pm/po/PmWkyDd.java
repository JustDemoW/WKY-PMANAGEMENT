package com.sys.pm.po;

/**
 * PmWkyDd entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyDd implements java.io.Serializable {

	// Fields

	private String ddId;
	private String ddKey;
	private String ddCodeValue;
	private String ddValue;
	private String ddPinyin;

	// Constructors

	/** default constructor */
	public PmWkyDd() {
	}

	/** minimal constructor */
	public PmWkyDd(String ddId, String ddKey, String ddValue) {
		this.ddId = ddId;
		this.ddKey = ddKey;
		this.ddValue = ddValue;
	}

	/** full constructor */
	public PmWkyDd(String ddId, String ddKey, String ddCodeValue,
			String ddValue, String ddPinyin) {
		this.ddId = ddId;
		this.ddKey = ddKey;
		this.ddCodeValue = ddCodeValue;
		this.ddValue = ddValue;
		this.ddPinyin = ddPinyin;
	}

	// Property accessors

	public String getDdId() {
		return this.ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public String getDdKey() {
		return this.ddKey;
	}

	public void setDdKey(String ddKey) {
		this.ddKey = ddKey;
	}

	public String getDdCodeValue() {
		return this.ddCodeValue;
	}

	public void setDdCodeValue(String ddCodeValue) {
		this.ddCodeValue = ddCodeValue;
	}

	public String getDdValue() {
		return this.ddValue;
	}

	public void setDdValue(String ddValue) {
		this.ddValue = ddValue;
	}

	public String getDdPinyin() {
		return this.ddPinyin;
	}

	public void setDdPinyin(String ddPinyin) {
		this.ddPinyin = ddPinyin;
	}

}