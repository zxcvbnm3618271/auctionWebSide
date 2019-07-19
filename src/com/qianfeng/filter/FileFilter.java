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

public class FileFilter implements Filter {

	Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) {
		// arg0 arg1 分别转成http的
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 获取到用户访问的文件
		// 比如用户访问的是login.jsp 那么uri=/auction/login.jsp
		String useFile = request.getRequestURI();
		// indexof("/",1) 代表搜索 / 这个关键字 然后是从第二个字符开始搜索 返回这个 / 字符所在的位置
		useFile.substring(useFile.indexOf("/", 1), useFile.length());
		if (useFile.contains("login.jsp")
				|| useFile.contains("AuctionLoginServlet")
				|| useFile.contains("Number.jsp")
				|| useFile.contains("/images") || useFile.contains("/js")
				|| useFile.contains("/css")) {

			// 放行操作
			try {

				String userAddr = request.getLocalAddr();
				String userMethod = request.getMethod();
				// password&username&inputcode
				String userArgs = "";
				if (StringUtil.notEmpty(request.getQueryString())) {
					userArgs = request.getQueryString();
				}
				
				
				String userfile = request.getRequestURI();

				logger.info("用户的地址:"
						+ userAddr
						+ ",用户的请求方式"
						+ userMethod
						+ ",用户的传参:"
						+ Arrays.toString(userArgs.split("&"))
						+ ",用户访问的文件:"
						+ useFile.substring(userfile.indexOf("/", 1),
								userfile.length()) + "");

				arg2.doFilter(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			AuctionUser auctionUser = (AuctionUser) request.getSession()
					.getAttribute("user");
			// 如果没有登陆过,就跳转到登录页面
			if (auctionUser == null) {
				try {
					response.sendRedirect("login.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			} else {
				try {
					
					String userAddr = request.getLocalAddr();
					String userMethod = request.getMethod();
					// password&username&inputcode
					String userArgs = "";
					if (StringUtil.notEmpty(request.getQueryString())) {
						userArgs = request.getQueryString();
					}
					
					
					String userfile = request.getRequestURI();

					logger.info("用户的地址:"
							+ userAddr
							+ ",用户的请求方式"
							+ userMethod
							+ ",用户的传参:"
							+ Arrays.toString(userArgs.split("&"))
							+ ",用户访问的文件:"
							+ useFile.substring(userfile.indexOf("/", 1),
									userfile.length()) + "");
					
					arg2.doFilter(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e.getMessage());
				} catch (ServletException e) {
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
