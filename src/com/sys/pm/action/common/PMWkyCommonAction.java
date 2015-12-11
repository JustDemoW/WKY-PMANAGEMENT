package com.sys.pm.action.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.pm.common.BaseAction;
import com.sys.pm.po.PmWkyDd;
import com.sys.pm.po.PmWkyDdDAO;

public class PMWkyCommonAction extends BaseAction{
	private String className;
	private Map<String, Object> map = new HashMap<String, Object>();
	private boolean success = true;
	private String errno = "-1";
	public String findDDLike(){
		PmWkyDdDAO ddDAO = new PmWkyDdDAO();
		List<PmWkyDd> dd = ddDAO.findByDdKey(className);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> xxMap = null;
		for (PmWkyDd pmWkyDd : dd) {
			xxMap = new HashMap<String, Object>();
			xxMap.put("ddValue", pmWkyDd.getDdValue());
			xxMap.put("ddCodeValue", pmWkyDd.getDdCodeValue());
			list.add(xxMap);
		}
		map.put("result", list);
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getErrno() {
		return errno;
	}
}
