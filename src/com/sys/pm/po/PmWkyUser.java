package com.sys.pm.po;

/**
 * PmWkyUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PmWkyUser implements java.io.Serializable {

	// Fields

	private String userName;
	private String userPassword;
	private String userNickname;
	private String userQx;
	private String userLastlogintime;
	private String userLastloginip;
	private String userSex;

	// Constructors

	/** default constructor */
	public PmWkyUser() {
	}

	/** minimal constructor */
	public PmWkyUser(String userName, String userPassword,
			String userLastlogintime, String userLastloginip) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userLastlogintime = userLastlogintime;
		this.userLastloginip = userLastloginip;
	}

	/** full constructor */
	public PmWkyUser(String userName, String userPassword, String userNickname,
			String userQx, String userLastlogintime, String userLastloginip,
			String userSex) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userNickname = userNickname;
		this.userQx = userQx;
		this.userLastlogintime = userLastlogintime;
		this.userLastloginip = userLastloginip;
		this.userSex = userSex;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserNickname() {
		return this.userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserQx() {
		return this.userQx;
	}

	public void setUserQx(String userQx) {
		this.userQx = userQx;
	}

	public String getUserLastlogintime() {
		return this.userLastlogintime;
	}

	public void setUserLastlogintime(String userLastlogintime) {
		this.userLastlogintime = userLastlogintime;
	}

	public String getUserLastloginip() {
		return this.userLastloginip;
	}

	public void setUserLastloginip(String userLastloginip) {
		this.userLastloginip = userLastloginip;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

}