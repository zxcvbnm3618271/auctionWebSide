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
import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.bizimpl.AuctionUserBIZImpl;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.util.JDBCUtil;

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
	// servlet接收用户请求和响应用户条件 tomcat 是jsp和servlet容器
	// http是由请求体和响应体组成 后面参数是抽象 和封装
	// request接收请求 请求行（请求方法get post ），用户请求文件地址，http版本 ；
	// 请求头（浏览器内核，操作系统信息，cookies） 请求体（向服务器传数据value和key）
	// response3部分 响应状态200 500；响应头html json plain 响应体（用户看到的数据）
	// get用户请求操作 暴露请求地址 数据量有限制
	// post 用户提交操作 隐藏提交数据 数据量无限制
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.getWriter().print("hello");
		//
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
	
	//三层开发规范 上层依赖于下层 ps:删除DAO层  biz和view报错
	//删除业务层BIZ后 DAO没报错则代码不规范
	//MVC M:model dao层+entity
	    //V：view  jsp
	    //C:controller  servlet   mvc视图框架structs
	//把throws抛出的异常删除 控制层不能抛出异常会用户捕获
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
          //获取用户提交的参数 用户名 密码 验证码
		String userName=request.getParameter("username01");
		String passWord=request.getParameter("userpassword01");
	    String userInputCode=request.getParameter("inputCode");
	    
	    String sysCode=(String)request.getSession().getAttribute("numrand");
	    
	    //三层思想结合MVC思想开发思路
	    AuctionUserBIZ auctionUserBIZ=new AuctionUserBIZImpl();
	    
	    try {
	    	String loginState=auctionUserBIZ.auctionUserLogin(userName, passWord, userInputCode, sysCode,request);
	    	if (loginState.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS.getValue())) {
	    		request.getRequestDispatcher(
	    				"AuctionListServlet?msg="+loginState+"").forward(request,response);
			}else {
				request.getRequestDispatcher("login.jsp?msg="+loginState+"").forward(request,response);
			}
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//ctrl+shift+f 实现谷歌编码规范    F11运行主函数
	public static void main(String[] args) {
		// jdbc操作步骤
		java.sql.Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// ctrl+1自动修复
		// 加载厂商给的驱动包
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 实例化connection
			connection =JDBCUtil.getConnection();
			// 实例化数据库实例
			// 执行sql语句
			preparedStatement = connection
					.prepareStatement("select * from auctionuser");
			resultSet = preparedStatement.executeQuery();
			// 遍历结果集next 有数据返回true 无就false
			while (resultSet.next()) {

				String userName = resultSet.getString("USERNAME");
				System.out.println(userName);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 快捷键shift+alt+z 解决异常
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
	}

}
