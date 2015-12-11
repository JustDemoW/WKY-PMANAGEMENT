package com.sys.pm.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.pm.common.Constant;
import com.sys.pm.util.HibernateSessionFactory;

public class PMInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> ssession = invocation.getInvocationContext().getSession();
		if (ssession.get(Constant.USER_KEY)==null) {
			String uri = ServletActionContext.getRequest().getRequestURI();
			ssession.put(Constant.URI_KEY, uri);
			return "notLogin";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction tra = session.beginTransaction();
		try{
			invocation.invoke();
			tra.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tra.rollback();
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
		return null;
	}
	
}
