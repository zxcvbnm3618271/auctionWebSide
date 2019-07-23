package com.qianfeng.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qianfeng.entity.AuctionUser;
import com.qianfeng.util.StringUtil;

public class FileFilter implements Filter{

	Logger logger=Logger.getLogger(getClass());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		
		
		String usAddr= request.getLocalAddr();
		String usMethod= request.getMethod();
		String usArgs= "";
		String usFile=request.getRequestURI();
		
		if (StringUtil.notEmpty(request.getQueryString())) {
			usArgs=request.getQueryString();
		}
		
		logger.info("用户的地址：" + usAddr +",用户的请求方式：" 
		+ usMethod
		+",用户的传参："
		+Arrays.toString(usArgs.split("&"))
		+",用户访问的文件："
		+usFile.substring(usFile.indexOf("/", 1), usFile.length())
		+"");
	
		usFile=usFile.substring(usFile.indexOf("/", 1), usFile.length());
		if (usFile.contains("login.jsp")||usFile.contains("Number.jsp")
				||usFile.contains("AuctionLoginServlet")
				||usFile.contains("/images")||usFile.contains("/js")
				||usFile.contains("/css")) {
			try {
				arg2.doFilter(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			
			AuctionUser auctionUser=(AuctionUser) request.getSession()
					.getAttribute("user");
			if (auctionUser==null) {
				try {
					response.sendRedirect("login.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}else {
				try {
					
					
					arg2.doFilter(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} 
			}
			
			
			
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
