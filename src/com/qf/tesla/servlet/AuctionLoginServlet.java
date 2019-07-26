package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.tesla.biz.AuctionUserBIZ;
import com.qf.tesla.bizimpl.AuctionUserBIZImpl;
import com.qf.tesla.enums.AuctionLoginStateEnum;
import com.qf.tesla.util.JDBCUtil;

@Entity
public class AuctionLoginServlet extends HttpServlet {

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
	// tomcat实质上是servlet 和jsp 的容器
	// http由请求体和响应体组成
	// request的职能是接受用户的请求，response的职能是相应用户的请求
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// response.getWriter().print("hello");

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
	// springMVC
	// M：model,指的是dao+entity
	// V: view ,jsp
	// C: controller,指的是servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			String userName = request.getParameter("username01");
			String passWord = request.getParameter("userpassword01");
			String userInputcode = request.getParameter("inputCode");

			String sysCode = (String) request.getSession().getAttribute(
					"numrand");

			AuctionUserBIZ auctionUserBIZ = new AuctionUserBIZImpl();
			String msg = auctionUserBIZ.auctionUserLogin(userName, passWord,
					userInputcode, sysCode,request);
			if (msg.equals(AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS.getValue())) {
				request.getRequestDispatcher("AuctionListServlet?msg="+msg).forward(
						request, response);
			}
			else{
				request.getRequestDispatcher("login.jsp?msg=" + msg).forward(
						request, response);
			}
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Connection connection = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			statement = connection
					.prepareStatement("SELECT * FROM AUCTIONUSER");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("USERNAME"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, statement, connection);

		}
	}
}
