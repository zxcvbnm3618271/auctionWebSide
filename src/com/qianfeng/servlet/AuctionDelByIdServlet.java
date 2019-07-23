package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;

public class AuctionDelByIdServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

		String auctionID=request.getParameter("auctionid");
		AuctionBIZ auctionBIZ=new AuctionBIZImpl();
	
			int resultCount=auctionBIZ
					.auctionDelByID(Integer.parseInt(auctionID));
			//删除操作一般分为两种一种是真删除  一种是假删除（isdel1代表删除 2代表正常）
			if (resultCount>0) {
				//*成功回调函数的值实际上就是请求体中响应正文接口和ajax的成功回调函数实际就是获取
				//响应体的响应正文
				//>0代表删除成功
				response.getWriter().print(true);
			}else {
				response.getWriter().print(false);
			}
		
	}

}
