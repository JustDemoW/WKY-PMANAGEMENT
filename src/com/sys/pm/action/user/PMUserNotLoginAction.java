package com.sys.pm.action.user;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyUser;
import com.sys.pm.po.PmWkyUserDAO;
import com.sys.pm.util.CookieUtil;
import com.sys.pm.util.EncryptUtil;
import com.ygdk.util.StringUtil;

public class PMUserNotLoginAction extends BaseAction {
	//output
	private String errno = "-6";
	private boolean success = true;
	public String notLogin() {
		return "success";
	}
	public String getErrno() {
		return errno;
	}
	public boolean isSuccess() {
		return success;
	}
}
