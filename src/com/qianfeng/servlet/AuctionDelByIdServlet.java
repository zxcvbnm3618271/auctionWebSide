package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.biz.Impl.AuctionBIZImpl;

public class AuctionDelByIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionDelByIdServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String auctionID =request.getParameter("auctionid");
	AuctionBIZ auctionBIZ=new AuctionBIZImpl();
	int resultCount=auctionBIZ.auctionDelByID(Integer.parseInt(auctionID));
	if (resultCount>0) {
		//成功回调函数的值实际上就是请求体中的相应正文接口和ajax的成功回调函数 响应体和相应正文
		response.getWriter().print(true);
	
	}else {
		response.getWriter().print(false);
	}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
