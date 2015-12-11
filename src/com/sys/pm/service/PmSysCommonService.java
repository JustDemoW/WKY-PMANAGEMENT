package com.sys.pm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.pm.po.PmWkyDd;
import com.sys.pm.po.PmWkyDdDAO;

public class PmSysCommonService {
	private static PmSysCommonService commonService = null;

	private PmSysCommonService() {

	}

	public static PmSysCommonService getInstance() {
		if (commonService == null)
			commonService = new PmSysCommonService();
		return commonService;
	}

	public Map getDDmapByDdkey(String ddKey) {
		Map map = new HashMap();
		PmWkyDdDAO ddDAO = new PmWkyDdDAO();
		List<PmWkyDd> ddList = ddDAO.findByDdKey(ddKey);
		for (PmWkyDd pmWkyDd : ddList) {
			map.put(pmWkyDd.getDdCodeValue(), pmWkyDd.getDdValue());
		}
		return map;
	}
}
