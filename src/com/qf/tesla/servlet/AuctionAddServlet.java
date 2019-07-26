package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.enums.AuctionStateEnum;
import com.qf.tesla.util.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Entity
public class AuctionAddServlet extends HttpServlet {

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
		// 文件上传
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		String auctionState = null;
		try {
//			auctionState = auctionBIZ.auctionAdd(getServletConfig(), request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(auctionState.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getVaule()));{
			try {
				request.getRequestDispatcher("AuctionListServlet?msg="+auctionState).forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		SmartUpload smartUpload = new SmartUpload();
//		try {
//			// 知道用户上传的文件和提交的数据
//			smartUpload.initialize(getServletConfig(), request, response);
//			// 设置文件上传的大小
//			smartUpload.setMaxFileSize(1024 * 10 * 1024);
//			// 开启上传功能
//			smartUpload.upload();
//			// 获取文件,SmartUPload封装的File类
//			File userFile = smartUpload.getFiles().getFile(0);
//			// 获取文件后缀名
//			String fileText = userFile.getFileExt();
//			// 生成文件名 ，我们使用时间戳
//			String fileName = new SimpleDateFormat("yyyyMMddHHmmssSS")
//					.format(new Date());
//			fileName = fileName + "." + fileText;
//			// 获取服务器上的upload文件夹的路径，用户上传文件不会存在本机上，而是在web容器或者数据库中。前者适合小文件，易于管理，但是性能消耗打，后者则相反
//			String hostPath = request.getSession().getServletContext()
//					.getRealPath("upload");
//			// 存储文件 使用File.separator而不是/具有跨平台性
//			userFile.saveAs(hostPath + java.io.File.separator + fileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			// id不与要插入，由自增实现
			// 一行代码控制在80字以内
			preparedStatement = connection
					.prepareStatement("insert into auction(AUCTIONNAME,AUCTIONSTARTPRICE,AUCTIONUPSET,AUCTIONSTARTTIME,"
							+ "AUCTIONENDTIME,AUCTIONDESC,AUCTIONPICPATH,CREATETIME,UPDATETIME) "
							+ "values(?,?,?,?,?,?,?,?,?)");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			preparedStatement.setString(1, "青龙偃月刀");
			preparedStatement.setDouble(2, 100000d);
			preparedStatement.setDouble(3, 10000d);
			preparedStatement.setTimestamp(4,
					Timestamp.valueOf("2011-11-11 11:11:11"));
			preparedStatement.setTimestamp(5,
					Timestamp.valueOf("2011-11-11 11:41:11"));
			preparedStatement.setString(6, "三国名器");
			preparedStatement.setString(7, "");
			preparedStatement.setTimestamp(8,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9,
					new Timestamp(System.currentTimeMillis()));
			resultCount = preparedStatement.executeUpdate();
			if (resultCount > 0) {
				System.out.println("插入成功");
			} else {
				System.out.println("插入失败");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
