package com.sys.pm.action.consumption;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.FastDateFormat;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.common.DdNamebean;
import com.sys.pm.po.PmWkyConsumptionrecord;
import com.sys.pm.po.PmWkyConsumptionrecordDAO;
import com.sys.pm.po.PmWkyDd;
import com.sys.pm.po.PmWkyDdDAO;
import com.sys.pm.po.PmWkyUser;
import com.sys.pm.service.PmSysCommonService;
import com.ygdk.util.DateTimeUtil;

public class PMSysGetConsumptionAction extends BaseAction {
	private String start, limit, searchDateType, searchYear, searchStartTime,
			searchEndTime;
	private Map<String, Object> map = new HashMap<String, Object>();
	private boolean success = true;
	private String errno = "-1";

	public String findConsumptionInfo() {
		String startTime = "";
		String endTime = "";
		if ("1".equals(searchDateType)) {
			startTime = DateTimeUtil.getTodayChar8() + "000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("2".equals(searchDateType)) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Date yesterday = calendar.getTime();
			FastDateFormat df = FastDateFormat.getInstance("yyyyMMdd");
			String yesterDaStr = df.format(yesterday);
			startTime = yesterDaStr + "000000";
			endTime = yesterDaStr + "235959";
		} else if ("3".equals(searchDateType)) {
			int mondayPlus;
			Calendar cd = Calendar.getInstance();
			// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
			int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
			if (dayOfWeek == 1) {
				mondayPlus = 0;
			} else {
				mondayPlus = 1 - dayOfWeek;
			}
			cd.add(Calendar.DATE, mondayPlus);
			Date monday = cd.getTime();
			FastDateFormat df = FastDateFormat.getInstance("yyyyMMdd");
			String modayStr = df.format(monday);
			startTime = modayStr + "000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("4".equals(searchDateType)) {
			String month = DateTimeUtil.getTodayChar6();
			startTime = month + "01000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("5".equals(searchDateType)) {
			startTime = searchStartTime;
			endTime = searchEndTime;
		}
		int startInt = Integer.parseInt(start);
		int limitInt = Integer.parseInt(limit);
		PmWkyUser userBean = (PmWkyUser) session.get(Constant.USER_KEY);
		PmWkyConsumptionrecordDAO crDAO = new PmWkyConsumptionrecordDAO();
		List<PmWkyConsumptionrecord> crList = crDAO.findByFRecordTime(startInt,
				limitInt, startTime, endTime, userBean.getUserName());
		int total = crDAO.countByFRecordTime(startTime, endTime, userBean
				.getUserName());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> xxMap = null;
		PmSysCommonService commonService = PmSysCommonService.getInstance();
		Map<String, String> xflxddMap = commonService
				.getDDmapByDdkey(DdNamebean.DD_XFLX);
		Map<String, String> cxlxddMap = commonService
				.getDDmapByDdkey(DdNamebean.DD_CXLX);
		Map<String, String> srxddMap = commonService
		.getDDmapByDdkey(DdNamebean.DD_SRLX);
		for (PmWkyConsumptionrecord pmWkyConsumptionrecord : crList) {
			xxMap = new HashMap<String, Object>();
			xxMap.put("recordId", pmWkyConsumptionrecord.getRecordId());
			xxMap.put("recordConsumptioncredit", pmWkyConsumptionrecord
					.getRecordConsumptioncredit());
			if("1".equals(pmWkyConsumptionrecord.getRecordType()))
			{
				xxMap.put("recordConsumptiontype", srxddMap
						.get(pmWkyConsumptionrecord.getRecordConsumptiontype()));
			}else{
				xxMap.put("recordConsumptiontype", xflxddMap
						.get(pmWkyConsumptionrecord.getRecordConsumptiontype()));
			}
			xxMap.put("recordDeposittype", cxlxddMap.get(pmWkyConsumptionrecord
					.getRecordDeposittype()));
			xxMap.put("recordRemarks", pmWkyConsumptionrecord
					.getRecordRemarks());
			xxMap.put("recordTime", DateTimeUtil
					.getFormatNormal(pmWkyConsumptionrecord.getRecordTime()));
			xxMap.put("recordType", "1".equals(pmWkyConsumptionrecord
					.getRecordType()) ? "收入" : "消费");
			list.add(xxMap);
		}
		map.put("result", list);
		map.put("total", total);
		errno = "0";
		return "success";
	}

	public String findChartsStore() {
		String startTime = "";
		String endTime = "";
		if ("1".equals(searchDateType)) {
			startTime = DateTimeUtil.getTodayChar8() + "000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("2".equals(searchDateType)) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			Date yesterday = calendar.getTime();
			FastDateFormat df = FastDateFormat.getInstance("yyyyMMdd");
			String yesterDaStr = df.format(yesterday);
			startTime = yesterDaStr + "000000";
			endTime = yesterDaStr + "235959";
		} else if ("3".equals(searchDateType)) {
			int mondayPlus;
			Calendar cd = Calendar.getInstance();
			// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
			int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
			if (dayOfWeek == 1) {
				mondayPlus = 0;
			} else {
				mondayPlus = 1 - dayOfWeek;
			}
			cd.add(Calendar.DATE, mondayPlus);
			Date monday = cd.getTime();
			FastDateFormat df = FastDateFormat.getInstance("yyyyMMdd");
			String modayStr = df.format(monday);
			startTime = modayStr + "000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("4".equals(searchDateType)) {
			String month = DateTimeUtil.getTodayChar6();
			startTime = month + "01000000";
			endTime = DateTimeUtil.getTodayChar14();
		} else if ("5".equals(searchDateType)) {
			startTime = searchStartTime;
			endTime = searchEndTime;
		}
		PmWkyDdDAO ddDAO = new PmWkyDdDAO();
		List<PmWkyDd> xflx = ddDAO.findByDdKey(DdNamebean.DD_XFLX);
		List<Map<String, Object>> xxList = new ArrayList<Map<String, Object>>();
		Map<String, Object> xxMap = null;
		PmWkyConsumptionrecordDAO consumptionrecordDAO = new PmWkyConsumptionrecordDAO();
		PmWkyUser userBean = (PmWkyUser) session.get(Constant.USER_KEY);
		List<PmWkyConsumptionrecord> crList = consumptionrecordDAO.findByDate(
				startTime, endTime, userBean.getUserName());
		for (PmWkyDd pmWkyDd : xflx) {
			xxMap = new HashMap<String, Object>();
			double data = 0;
			for (PmWkyConsumptionrecord pmWkyConsumptionrecord : crList) {
				if (pmWkyDd.getDdCodeValue().equals(
						pmWkyConsumptionrecord.getRecordConsumptiontype())&&
						"2".equals(pmWkyConsumptionrecord.getRecordType())) {
					data += pmWkyConsumptionrecord.getRecordConsumptioncredit();
				}
			}
			xxMap.put("name", pmWkyDd.getDdValue());
			xxMap.put("data", data);
			xxList.add(xxMap);
		}
		map.put("result", xxList);
		errno = "0";
		return "success";
	}

	public String findChartsStoreForYear() {
		String startTime = searchYear+"0101000000";
		String endTime = searchYear+"1231235959";
		String[] mouthArr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		String year = DateTimeUtil.getTodayYear();
		String tMouth = DateTimeUtil.getTodayMonth();
		Map<String,Double> dataMap = new HashMap<String, Double>();
		for (String mouth : mouthArr) {
			dataMap.put(mouth, 0.00);
		}
		PmWkyConsumptionrecordDAO consumptionrecordDAO = new PmWkyConsumptionrecordDAO();
		PmWkyUser userBean = (PmWkyUser) session.get(Constant.USER_KEY);
		List<PmWkyConsumptionrecord> crList = consumptionrecordDAO.findByDate(
				startTime, endTime, userBean.getUserName());
		List<Map<String,Object>> xxList = new ArrayList<Map<String,Object>>();
		for (PmWkyConsumptionrecord cr : crList) {
			long date = Long.valueOf(cr.getRecordTime());
			for (String mouth : mouthArr) {
				long startDate = Long.valueOf(searchYear+mouth+"01000000");
				long endDate = Long.valueOf(searchYear+mouth+"31235959");
				if(date>=startDate&&date<=endDate){
					double data = dataMap.get(mouth);
					data+=cr.getRecordConsumptioncredit();
					dataMap.put(mouth, data);
				}
				if(searchYear.equals(year)&&mouth.equals(tMouth)){
					break;
				}
			}
		}
		Map<String,Object> xxMap = null;
		for (String mouth : mouthArr) {
			xxMap = new HashMap<String, Object>();
			double data = dataMap.get(mouth);
			xxMap.put("data", data);
			xxMap.put("name", mouth+"月");
			xxList.add(xxMap);
			if(searchYear.equals(year)&&mouth.equals(tMouth)){
				break;
			}
		}
		map.put("result", xxList);
		errno = "0";
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getErrno() {
		return errno;
	}

	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}

	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public static void main(String[] args) {
		System.out.println(2-4);
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
}
