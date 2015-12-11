package com.sys.pm.action.system;

import java.util.ArrayList;
import java.util.List;

import com.sys.pm.common.BaseAction;
import com.sys.pm.common.Constant;
import com.sys.pm.po.PmWkyModule;
import com.sys.pm.po.PmWkyModuleDAO;
import com.sys.pm.po.PmWkyModuleFile;
import com.sys.pm.po.PmWkyModuleFileDAO;
import com.sys.pm.po.PmWkyUser;

public class PMSysGetModuleAction extends BaseAction {
	// input
	private String moduleId;
	// output
	private PmWkyModule module;
	private PmWkyModuleFile moduleFile;
	private String errno = "-1";

	public String findModule() throws Exception {
		PmWkyModuleDAO moduleDAO = new PmWkyModuleDAO();
		List<PmWkyModule> moduleList = moduleDAO.findByModuleId(moduleId);
		if (moduleList != null && moduleList.size() > 0) {
			module = moduleList.get(0);
			if ("1".equals(module.getLoginVerify())) {//需要登录验证的模块
				PmWkyUser user = (PmWkyUser)session.get(Constant.USER_KEY);
				if(user==null){
					errno = "-6";
					return "success";
				}
			}
			PmWkyModuleFileDAO fileDAO = new PmWkyModuleFileDAO();
			List<PmWkyModuleFile> fileList = fileDAO.findByProperty(
					"id.moduleId", module.getId());
			if (fileList != null && fileList.size() > 0) {
				moduleFile = fileList.get(0);
			}
			errno = "0";
		}
		return "success";
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public PmWkyModule getModule() {
		return module;
	}

	public void setModule(PmWkyModule module) {
		this.module = module;
	}


	public String getErrno() {
		return errno;
	}

	public PmWkyModuleFile getModuleFile() {
		return moduleFile;
	}

	public void setModuleFile(PmWkyModuleFile moduleFile) {
		this.moduleFile = moduleFile;
	}

}
