package com.sys.pm.action.consumption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.common.DdNamebean;
import com.sys.pm.po.PmWkyConsumptionrecord;
import com.sys.pm.po.PmWkyConsumptionrecordDAO;
import com.sys.pm.po.PmWkyDd;
import com.sys.pm.po.PmWkyDdDAO;
import com.sys.pm.po.PmWkyUser;

public class PMSysGetstatisticsDataAction extends BaseAction {
	private double salary = 0.00, xykxf = 0.00, trjc = 0.00, trjr = 0.00,
			zsr = 0.00, zzc = 0.00, jy = 0.00;
	private String errno = "-1";

	public String findStatisticsData() {
		PmWkyConsumptionrecordDAO crDAO = new PmWkyConsumptionrecordDAO();
		PmWkyDdDAO ddDAO = new PmWkyDdDAO();
		List<PmWkyDd> srlxList = ddDAO.findByDdKeyAndDdValue(
				DdNamebean.DD_SRLX, "工资");
		String salaryDdCodevalue = "";
		if (srlxList != null && srlxList.size() > 0) {
			salaryDdCodevalue = srlxList.get(0).getDdCodeValue();
		}
		List<PmWkyDd> trjrList = ddDAO.findByDdKeyAndDdValue(
				DdNamebean.DD_SRLX, "他人借入");
		String trjrDdCodevalue = "";
		if (trjrList != null && trjrList.size() > 0) {
			trjrDdCodevalue = trjrList.get(0).getDdCodeValue();
		}
		List<PmWkyDd> trjcList = ddDAO.findByDdKeyAndDdValue(
				DdNamebean.DD_XFLX, "他人借出");
		String trjcDdCodevalue = "";
		if (trjcList != null && trjcList.size() > 0) {
			trjcDdCodevalue = trjcList.get(0).getDdCodeValue();
		}
		List<PmWkyDd> cxlxList = ddDAO.findByDdKeyAndDdValue(
				DdNamebean.DD_CXLX, "贷记卡");
		String djkCodeValue = "";
		if (cxlxList != null && cxlxList.size() > 0) {
			djkCodeValue = cxlxList.get(0).getDdCodeValue();
		}
		PmWkyUser userBean = (PmWkyUser) session.get(Constant.USER_KEY);
		List<PmWkyConsumptionrecord> crList = crDAO
				.findByRecordUsernane(userBean.getUserName());
		for (PmWkyConsumptionrecord cr : crList) {
			if ("1".equals(cr.getRecordType())
					&& cr.getRecordConsumptiontype().equals(salaryDdCodevalue)) {
				salary += cr.getRecordConsumptioncredit();
			}
			if (cr.getRecordDeposittype().equals(djkCodeValue)
					&& "2".equals(cr.getRecordType())) {
				xykxf += cr.getRecordConsumptioncredit();
			}
			if ("1".equals(cr.getRecordType())
					&& cr.getRecordConsumptiontype().equals(trjrDdCodevalue)) {
				trjr += cr.getRecordConsumptioncredit();
			}
			if ("2".equals(cr.getRecordType())
					&& cr.getRecordConsumptiontype().equals(trjcDdCodevalue)) {
				trjc += cr.getRecordConsumptioncredit();
			}
			if ("1".equals(cr.getRecordType())) {
				zsr += cr.getRecordConsumptioncredit();
			}
			if ("2".equals(cr.getRecordType())) {
				zzc += cr.getRecordConsumptioncredit();
			}
			jy = zsr - zzc;
		}
		errno = "0";
		return "success";
	}

	public double getSalary() {
		return salary;
	}

	public double getXykxf() {
		return xykxf;
	}

	public double getTrjc() {
		return trjc;
	}

	public double getTrjr() {
		return trjr;
	}

	public double getZsr() {
		return zsr;
	}

	public double getZzc() {
		return zzc;
	}

	public double getJy() {
		return jy;
	}

	public String getErrno() {
		return errno;
	}
}
