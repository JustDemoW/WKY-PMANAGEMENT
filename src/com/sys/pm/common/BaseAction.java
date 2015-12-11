package com.sys.pm.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
/**
 * action中需要的session，request，response等对象
 * @author Administrator
 *
 */
public class BaseAction implements RequestAware,ServletRequestAware,SessionAware,ServletResponseAware,ApplicationAware{
	protected Map<String, Object> request;
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest =  httpRequest;
	}
	public void setServletResponse(HttpServletResponse httpResponse){
		this.httpResponse = httpResponse;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
		
	}
}
