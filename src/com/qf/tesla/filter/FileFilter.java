package com.qf.tesla.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;

import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.util.StringUtil;

@Entity
public class FileFilter implements Filter {
	// 实例化，实现日志记录功能
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) {
		// TODO Auto-generated method stub
		// 两个参数转成http的
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		String usAddrr = request.getLocalAddr();
		String usMethod = request.getMethod();
		String usArgs ="";  // 获取用户提交参数
		String userFile = request.getRequestURI();
		
		if(StringUtil.notEmpty(request.getQueryString())){
			usArgs = request.getQueryString();
		}
		logger.info("用户的地址："
				+ usAddrr
				+ "用户的请求方式"
				+ usMethod
				+ "用户提交的参数"
				+ Arrays.toString(usArgs.split("&"))
				+ "用户访问的文件"
				+ userFile.substring(userFile.indexOf("/", 1),
						userFile.length()));
		
		// 获取用户的访问路径，uri = /项目名/文件名
		String usFile = request.getRequestURI();
		System.out.println("文件路径：" + usFile);
		// 获取文件名
		usFile = usFile.substring(usFile.indexOf("/", 1), usFile.length());
//		System.out.println("修改后的路径：" + usFile);
		// 和登入相关的所有文件都不应该被拦截
		
		if (usFile.contains("login.jsp") || usFile.contains("Number.jsp")
				|| usFile.contains("AuctionLoginServlet")
				|| usFile.contains("/js") || usFile.contains("/css")
				|| usFile.contains("/images")
				||userFile.contains("AuctionLogoutServlet")) {
			// 放行操作
			try {
				arg2.doFilter(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			AuctionUser user = (AuctionUser) request.getSession().getAttribute(
					"user");
			if (user == null) {
				try {
					response.sendRedirect("login.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				try {
					arg2.doFilter(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("");
				}
			}

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
