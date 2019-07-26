package com.qianfeng.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.qianfeng.entity.AuctionUser;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		
		AuctionUser auctionUser=(AuctionUser)ServletActionContext
				.getRequest().getSession().getAttribute("user");
		if (auctionUser==null) {
			return "login";
		}else {
			String result=arg0.invoke();
			return result;
		}
		
		
	
	}

}
