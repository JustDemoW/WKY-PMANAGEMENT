package com.sys.pm.po;

/**
 * PmWkyBankaccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyBankaccount implements java.io.Serializable {

	// Fields

	private String id;
	private String bankAccount;
	private String bankPassword;
	private String bankName;
	private String bankType;

	// Constructors

	/** default constructor */
	public PmWkyBankaccount() {
	}

	/** minimal constructor */
	public PmWkyBankaccount(String id, String bankAccount, String bankName,
			String bankType) {
		this.id = id;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.bankType = bankType;
	}

	/** full constructor */
	public PmWkyBankaccount(String id, String bankAccount, String bankPassword,
			String bankName, String bankType) {
		this.id = id;
		this.bankAccount = bankAccount;
		this.bankPassword = bankPassword;
		this.bankName = bankName;
		this.bankType = bankType;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankPassword() {
		return this.bankPassword;
	}

	public void setBankPassword(String bankPassword) {
		this.bankPassword = bankPassword;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

}