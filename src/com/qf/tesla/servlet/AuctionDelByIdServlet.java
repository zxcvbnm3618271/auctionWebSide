package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;

@Entity
public class AuctionDelByIdServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
			String auctionId = request.getParameter("auctionId");
			AuctionBIZ auctionBIZ = new AuctionBIZImpl();
			int resultCount = 0;
			try {
				resultCount = auctionBIZ.deleteById(Integer.parseInt(auctionId));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (resultCount>0) {
					//接口和ajax的成功回调函数的值就是请求体中的响应正文
					response.getWriter().print(true);//向请求正文中写数据
				}else{
					response.getWriter().print(false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
