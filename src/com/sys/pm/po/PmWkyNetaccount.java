package com.sys.pm.po;

/**
 * PmWkyNetaccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyNetaccount implements java.io.Serializable {

	// Fields

	private String accountId;
	private String accountName;
	private String accountPassword;
	private String accountSecpassword;
	private String accountLrsj;
	private String accountLrr;
	private String accountLrrxm;
	private String accountSfky;
	private String accountNetkeywords;
	private String accountNeturl;
	private String accountBz;

	// Constructors

	/** default constructor */
	public PmWkyNetaccount() {
	}

	/** minimal constructor */
	public PmWkyNetaccount(String accountId, String accountName,
			String accountPassword, String accountLrsj, String accountLrr,
			String accountSfky) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.accountLrsj = accountLrsj;
		this.accountLrr = accountLrr;
		this.accountSfky = accountSfky;
	}

	/** full constructor */
	public PmWkyNetaccount(String accountId, String accountName,
			String accountPassword, String accountSecpassword,
			String accountLrsj, String accountLrr, String accountLrrxm,
			String accountSfky, String accountNetkeywords,
			String accountNeturl, String accountBz) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.accountSecpassword = accountSecpassword;
		this.accountLrsj = accountLrsj;
		this.accountLrr = accountLrr;
		this.accountLrrxm = accountLrrxm;
		this.accountSfky = accountSfky;
		this.accountNetkeywords = accountNetkeywords;
		this.accountNeturl = accountNeturl;
		this.accountBz = accountBz;
	}

	// Property accessors

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountSecpassword() {
		return this.accountSecpassword;
	}

	public void setAccountSecpassword(String accountSecpassword) {
		this.accountSecpassword = accountSecpassword;
	}

	public String getAccountLrsj() {
		return this.accountLrsj;
	}

	public void setAccountLrsj(String accountLrsj) {
		this.accountLrsj = accountLrsj;
	}

	public String getAccountLrr() {
		return this.accountLrr;
	}

	public void setAccountLrr(String accountLrr) {
		this.accountLrr = accountLrr;
	}

	public String getAccountLrrxm() {
		return this.accountLrrxm;
	}

	public void setAccountLrrxm(String accountLrrxm) {
		this.accountLrrxm = accountLrrxm;
	}

	public String getAccountSfky() {
		return this.accountSfky;
	}

	public void setAccountSfky(String accountSfky) {
		this.accountSfky = accountSfky;
	}

	public String getAccountNetkeywords() {
		return this.accountNetkeywords;
	}

	public void setAccountNetkeywords(String accountNetkeywords) {
		this.accountNetkeywords = accountNetkeywords;
	}

	public String getAccountNeturl() {
		return this.accountNeturl;
	}

	public void setAccountNeturl(String accountNeturl) {
		this.accountNeturl = accountNeturl;
	}

	public String getAccountBz() {
		return this.accountBz;
	}

	public void setAccountBz(String accountBz) {
		this.accountBz = accountBz;
	}

}