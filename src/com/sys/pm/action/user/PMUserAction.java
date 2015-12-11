package com.sys.pm.action.user;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyDdDAO;
import com.sys.pm.po.PmWkyLoginrecord;
import com.sys.pm.po.PmWkyLoginrecordDAO;
import com.sys.pm.po.PmWkyUser;
import com.sys.pm.po.PmWkyUserDAO;
import com.sys.pm.util.EncryptUtil;
import com.ygdk.util.DateTimeUtil;
import com.ygdk.util.StringUtil;

public class PMUserAction extends BaseAction {
	private String userName = "";
	private String passWord = "";
	private String userNickname = "";
	private String userSex = "1";
	private String errno = "-1";
	private boolean success = true;
	public String login() {
		PmWkyUserDAO userDAO = new PmWkyUserDAO();
		PmWkyUser user = userDAO.findById(userName);
		if(user!=null){
//			EncryptUtil eu = new EncryptUtil();
//			eu.setKey(userName);
//			eu.setDesString(user.getUserPassword());
			//if(passWord.equals(eu.getStrM())){
			if(true){
				String userIp = httpRequest.getHeader("x-forwarded-for");
				if(userIp==null)
				{
					userIp = httpRequest.getRemoteAddr();
				}
				user.setUserLastloginip(userIp);
				user.setUserLastlogintime(DateTimeUtil.getTodayChar14());
				userDAO.merge(user);
				PmWkyLoginrecordDAO loginrecordDAO = new PmWkyLoginrecordDAO();
				PmWkyLoginrecord loginrecord = new PmWkyLoginrecord();
				loginrecord.setRecordId(StringUtil.createId25());
				loginrecord.setRecordLoginip(userIp);
				loginrecord.setRecordLogintime(DateTimeUtil.getTodayChar14());
				loginrecord.setRecordUsernane(userName);
				loginrecordDAO.save(loginrecord);
				session.put(Constant.USER_KEY, user);
				userName = user.getUserName();
				userSex = user.getUserSex();
				errno = "0";
			}
		}
		return "success";
	}
	public String register(){
//		EncryptUtil eu = new EncryptUtil();
//		eu.setKey(userName);
		PmWkyUserDAO userDAO = new PmWkyUserDAO();
		PmWkyUser user = new PmWkyUser();
		user.setUserLastloginip("1");
		user.setUserLastlogintime("1");
		user.setUserName(userName);
//		eu.setEncString(passWord);
//		user.setUserPassword(eu.getStrMi());
		user.setUserPassword(passWord);
		user.setUserNickname(userNickname);
		user.setUserSex(userSex);
		user.setUserQx("9");
		userDAO.save(user);
		errno = "0";
		return "success";
	}
	public String quit(){
		PmWkyUser user = (PmWkyUser)session.get(Constant.USER_KEY);
		if(user!=null){
			session.remove(Constant.USER_KEY);
		}
		return "success";
	}
	public String verify(){
		PmWkyUserDAO userDAO = new PmWkyUserDAO();
		PmWkyUser user = userDAO.findById(userName);
		if(user==null){
			errno = "0";
		}
		return "success";
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
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public static void main(String[] args) {
		PmWkyDdDAO dao = new PmWkyDdDAO();
		dao.findAll();
	}
}
