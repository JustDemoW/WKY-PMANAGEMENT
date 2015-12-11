package com.sys.pm.action.netAccount;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.sf.json.JSONObject;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyNetaccount;
import com.sys.pm.po.PmWkyNetaccountDAO;
import com.sys.pm.po.PmWkyUser;
import com.sys.pm.util.EncryptUtil;
import com.sys.pm.util.HibernateSessionFactory;
import com.sys.pm.util.MD5Util;
import com.ygdk.util.DateTimeUtil;
import com.ygdk.util.Md5Util;
import com.ygdk.util.StringUtil;

public class PMSysActionAccountinfoAction extends BaseAction {
	// input
	private String userName;
	private String passWord;
	private String secPassword;
	private String netURL;
	private String bz;
	private String accountId;
	private String type;
	// output
	private boolean success = true;
	private String errno = "-1";

	public String saveaccInfo() {
		PmWkyNetaccountDAO accDAO = new PmWkyNetaccountDAO();
		if ("update".equals(type)) {
			PmWkyNetaccount acc = accDAO.findById(accountId);
			//º”√‹
			EncryptUtil eu = new EncryptUtil();
			EncryptUtil eu2 = new EncryptUtil();
			eu.setKey(userName+passWord);
			eu2.setKey(userName);
			eu.setEncString(secPassword);
			eu2.setEncString(passWord);
			acc.setAccountBz(bz);
			acc.setAccountName(userName);
			acc.setAccountNeturl(netURL);
			acc.setAccountSecpassword(eu.getStrMi());
			System.out.println("password:"+eu2.getStrMi());
			acc.setAccountPassword(eu2.getStrMi());
			accDAO.merge(acc);
			errno = "0";
		} else if ("delete".equals(type)) {
			String[] ids = accountId.split(",");
			for (int i = 0; i < ids.length; i++) {
				PmWkyNetaccount acc = accDAO.findById(ids[i]);
				accDAO.delete(acc);
			}
			errno = "0";
		} else {
			PmWkyUser userBean = (PmWkyUser)session.get(Constant.USER_KEY);
			PmWkyNetaccount acc = new PmWkyNetaccount();
			EncryptUtil eu = new EncryptUtil();
			EncryptUtil eu2 = new EncryptUtil();
			eu.setKey(userName+passWord);
			eu2.setKey(userName);
			eu.setEncString(secPassword);
			eu2.setEncString(passWord);
			acc.setAccountBz(bz);
			acc.setAccountId(StringUtil.createId25());
			acc.setAccountLrr(userBean.getUserName());
			acc.setAccountLrrxm(userBean.getUserNickname());
			acc.setAccountLrsj(DateTimeUtil.getTodayChar8());
			acc.setAccountName(userName);
			acc.setAccountNeturl(netURL);
			acc.setAccountPassword(eu2.getStrMi());
			acc.setAccountSecpassword(eu.getStrMi());
			acc.setAccountSfky("1");
			accDAO.merge(acc);
			errno = "0";
		}
		return "success";
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSuccess() {
		return success;
	}


	public String getErrno() {
		return errno;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}

	public void setNetURL(String netURL) {
		this.netURL = netURL;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	public static void main(String[] args) {}
}
