package com.sys.pm.action.netAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import net.sf.json.JSONObject;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyNetaccount;
import com.sys.pm.po.PmWkyNetaccountDAO;
import com.sys.pm.po.PmWkyUser;
import com.sys.pm.util.EncryptUtil;
import com.ygdk.util.DateTimeUtil;
import com.ygdk.util.StringUtil;

public class PMSysFindAccountinfoAction extends BaseAction {
	//output
	private Map<String,Object> map = new HashMap<String, Object>();
	public String findaccInfo(){
		PmWkyUser userBean = (PmWkyUser)session.get(Constant.USER_KEY);
		PmWkyNetaccountDAO accDAO = new PmWkyNetaccountDAO();
		List<PmWkyNetaccount> accList = accDAO.findByAccountLrr(userBean.getUserName());
		if(accList!=null){
			List<PmWkyNetaccount> list = new ArrayList<PmWkyNetaccount>();
			for (int i = 0; i < accList.size(); i++) {
				PmWkyNetaccount acc = accList.get(i);
				PmWkyNetaccount newAcc = new PmWkyNetaccount();
				//½âÃÜ
				EncryptUtil eu = new EncryptUtil();
				EncryptUtil eu2 = new EncryptUtil();
				eu2.setKey(acc.getAccountName());
				eu2.setDesString(acc.getAccountPassword());
				String passWord = eu2.getStrM();
				eu.setKey(acc.getAccountName()+passWord);
				eu.setDesString(acc.getAccountSecpassword());
				String secPassword = eu.getStrM();
				newAcc.setAccountBz(StringUtil.null2String(acc.getAccountBz()));
				newAcc.setAccountId(acc.getAccountId());
				newAcc.setAccountLrr(acc.getAccountLrr());
				newAcc.setAccountLrrxm(acc.getAccountLrrxm());
				newAcc.setAccountLrsj(DateTimeUtil.getFormatNormal(acc.getAccountLrsj()));
				newAcc.setAccountName(acc.getAccountName());
				newAcc.setAccountNetkeywords(acc.getAccountNetkeywords());
				newAcc.setAccountNeturl(acc.getAccountNeturl());
				newAcc.setAccountPassword(passWord);
				newAcc.setAccountSecpassword(secPassword);
				newAcc.setAccountSfky(acc.getAccountSfky());
				list.add(newAcc);
			}
			map.put("result", list);
			map.put("total", accList.size());
		}
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public static void main(String[] args) {
	}
}
