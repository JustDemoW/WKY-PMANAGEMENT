package com.sys.pm.action.consumption;

import com.sys.pm.common.BaseAction;
import com.sys.pm.po.PmWkyConsumptionrecordDAO;

public class PMSysConsumptionAction extends BaseAction{
	private String type, recordId;
	private boolean success = true;
	private String errno = "-1";
	public String doAction(){
		if("add".equals(type)){
			
		}else if("update".equals(type)){
			
		}else if("delete".equals(type)){
			String[] idArr = recordId.split(",");
			PmWkyConsumptionrecordDAO crDAO = new PmWkyConsumptionrecordDAO();
			for (String id : idArr) {
				crDAO.delete(crDAO.findById(id));
			}
			errno = "0";
		}
		return "success";
	}
	public boolean isSuccess() {
		return success;
	}
	public String getErrno() {
		return errno;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
}
