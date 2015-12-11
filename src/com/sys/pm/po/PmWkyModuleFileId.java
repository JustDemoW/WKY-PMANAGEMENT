package com.sys.pm.po;

/**
 * PmWkyModuleFileId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyModuleFileId implements java.io.Serializable {

	// Fields

	private String moduleId;
	private String fileName;

	// Constructors

	/** default constructor */
	public PmWkyModuleFileId() {
	}

	/** full constructor */
	public PmWkyModuleFileId(String moduleId, String fileName) {
		this.moduleId = moduleId;
		this.fileName = fileName;
	}

	// Property accessors

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PmWkyModuleFileId))
			return false;
		PmWkyModuleFileId castOther = (PmWkyModuleFileId) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this
				.getModuleId() != null
				&& castOther.getModuleId() != null && this.getModuleId()
				.equals(castOther.getModuleId())))
				&& ((this.getFileName() == castOther.getFileName()) || (this
						.getFileName() != null
						&& castOther.getFileName() != null && this
						.getFileName().equals(castOther.getFileName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result
				+ (getFileName() == null ? 0 : this.getFileName().hashCode());
		return result;
	}

}