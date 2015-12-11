package com.sys.pm.action.system;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyUser;

public class PMSysIsLoginAction extends BaseAction {
	//output
	private boolean success = false;
	private String userName = "";
	private String userSex = "";
	public String isLogin(){
		PmWkyUser user = (PmWkyUser)session.get(Constant.USER_KEY);
		if(user!=null){
			userName = user.getUserName();
			userSex = user.getUserSex();
			success = true;
		}
		List<Map<String, String>> map = new ArrayList<Map<String,String>>();
		return "success";
	}
	public boolean isSuccess() {
		return success;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserSex() {
		return userSex;
	}
}
