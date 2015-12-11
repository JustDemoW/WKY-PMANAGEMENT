package com.sys.pm.interceptor;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.pm.util.HibernateSessionFactory;

public class PMTransactionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
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
