package com.qf.tesla.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.qf.tesla.entity.AuctionUser;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		//实现用户未登录不能访问action的拦截器
//		AuctionUser auctionUser = (AuctionUser)ServletActionContext.getRequest().getSession().getAttribute("user");
//		if (auctionUser==null) {
//			return "login";
//		}else{
//			String result = arg0.invoke();//放行
//			return result;
//		}
		
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()||subject.isRemembered()){
			String result = arg0.invoke();//放行
			return result;
		}else{
			return "login";
		}
	}

}
