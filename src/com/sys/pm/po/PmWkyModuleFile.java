package com.sys.pm.po;

/**
 * PmWkyModuleFile entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyModuleFile implements java.io.Serializable {

	// Fields

	private PmWkyModuleFileId id;
	private String directory;

	// Constructors

	/** default constructor */
	public PmWkyModuleFile() {
	}

	/** minimal constructor */
	public PmWkyModuleFile(PmWkyModuleFileId id) {
		this.id = id;
	}

	/** full constructor */
	public PmWkyModuleFile(PmWkyModuleFileId id, String directory) {
		this.id = id;
		this.directory = directory;
	}

	// Property accessors

	public PmWkyModuleFileId getId() {
		return this.id;
	}

	public void setId(PmWkyModuleFileId id) {
		this.id = id;
	}

	public String getDirectory() {
		return this.directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}