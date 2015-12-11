package com.sys.pm.po;

/**
 * PmWkyModule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyModule implements java.io.Serializable {

	// Fields

	private String id;
	private String directory;
	private String author;
	private String version;
	private String url;
	private String className;
	private String moduleType;
	private String moduleId;
	private String menuPath;
	private String launcherIconCls;
	private String launcherShortcutIconCls;
	private String launcherText;
	private String launcherTooltip;
	private String active;
	private String proload;
	private String description;
	private String sortOrder;
	private String loginVerify;

	// Constructors

	/** default constructor */
	public PmWkyModule() {
	}

	/** minimal constructor */
	public PmWkyModule(String id) {
		this.id = id;
	}

	/** full constructor */
	public PmWkyModule(String id, String directory, String author,
			String version, String url, String className, String moduleType,
			String moduleId, String menuPath, String launcherIconCls,
			String launcherShortcutIconCls, String launcherText,
			String launcherTooltip, String active, String proload,
			String description, String sortOrder, String loginVerify) {
		this.id = id;
		this.directory = directory;
		this.author = author;
		this.version = version;
		this.url = url;
		this.className = className;
		this.moduleType = moduleType;
		this.moduleId = moduleId;
		this.menuPath = menuPath;
		this.launcherIconCls = launcherIconCls;
		this.launcherShortcutIconCls = launcherShortcutIconCls;
		this.launcherText = launcherText;
		this.launcherTooltip = launcherTooltip;
		this.active = active;
		this.proload = proload;
		this.description = description;
		this.sortOrder = sortOrder;
		this.loginVerify = loginVerify;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDirectory() {
		return this.directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuPath() {
		return this.menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getLauncherIconCls() {
		return this.launcherIconCls;
	}

	public void setLauncherIconCls(String launcherIconCls) {
		this.launcherIconCls = launcherIconCls;
	}

	public String getLauncherShortcutIconCls() {
		return this.launcherShortcutIconCls;
	}

	public void setLauncherShortcutIconCls(String launcherShortcutIconCls) {
		this.launcherShortcutIconCls = launcherShortcutIconCls;
	}

	public String getLauncherText() {
		return this.launcherText;
	}

	public void setLauncherText(String launcherText) {
		this.launcherText = launcherText;
	}

	public String getLauncherTooltip() {
		return this.launcherTooltip;
	}

	public void setLauncherTooltip(String launcherTooltip) {
		this.launcherTooltip = launcherTooltip;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getProload() {
		return this.proload;
	}

	public void setProload(String proload) {
		this.proload = proload;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getLoginVerify() {
		return this.loginVerify;
	}

	public void setLoginVerify(String loginVerify) {
		this.loginVerify = loginVerify;
	}

}