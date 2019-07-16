package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.biz.Impl.AuctionBIZImpl;
import com.qianfeng.biz.Impl.AuctionUserBIZImpl;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.util.JDBCUtil;

public class AuctionLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionLoginServlet() {
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
		// PrintWriter outPrintWriter = response.getWriter();
		// outPrintWriter.print("hello");
		// 将doget 交给dopost处理
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
	// 控制层不能抛出异常,因为控制层抛出异常就会被用户捕获
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户提交的参数 用户名 密码 验证码
		// 从请求体中抓取对应的key

		String userName = request.getParameter("username01");
		String passWord = request.getParameter("userpassword01");
		String userInputCode = request.getParameter("inputCode");
		// 获取系统生成的验证码
		String sysCode = (String) request.getSession().getAttribute("numrand");

		AuctionUserBIZ auctionUserBIZ = new AuctionUserBIZImpl();
		// 调用业务逻辑层获取到对相应的返回值(用户登陆状态)
		String loginState = auctionUserBIZ.auctionUserLogin(userName, passWord,
				userInputCode, sysCode,request);
		// 带参重定向
		try {
			if (loginState.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS
					.getValue())) {
				request.getRequestDispatcher(
						"AuctionListServlet?msg=" + loginState + "").forward(
						request, response);
			} else {
				request.getRequestDispatcher("login.jsp?msg=" + loginState + "")
						.forward(request, response);
			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public static void main(String[] args) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			JDBCUtil.getConnection();
			JDBCUtil.query();
			JDBCUtil.close();

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
