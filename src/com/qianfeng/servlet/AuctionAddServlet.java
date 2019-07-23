package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionAddServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		AuctionBIZ auctionBIZ=new AuctionBIZImpl();
		try {
			String auctionState=auctionBIZ.auctionAdd(getServletConfig(), request, response);
			request.getRequestDispatcher("AuctionListServlet?msg="+auctionState+"").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    public static void main(String[] args) {
    	
    	
    	
    	
    	
//SmartUpload smartUpload=new SmartUpload();
		
//		try {
//			//初始化smartupload 加载完成后它就知道用户上传的文件和提交的参数
//			smartUpload.initialize(getServletConfig(), request, response);
//			//设置文件上传的大小
//			smartUpload.setMaxFileSize(1024*1024*10);
//			//开始上传
//
//		    smartUpload.upload();
//			//获取文件
//		    File userFile=smartUpload.getFiles().getFile(0);
//			//获取文件后缀名
//		    String fileEXT=userFile.getFileExt();
//			//生成文件名 使用时间戳
//		    String fileName=new SimpleDateFormat("yyyyMMddHHmmssSSS")
//			.format(new Date());
//			//合成文件名
//		    fileName=fileName+"."+fileEXT;
//			//获取服务器上upload文件夹路径 用户上传文件有两种存储形式
//			String hostPath=request.getSession().getServletContext()
//			.getRealPath("upload");
//			
//			userFile.saveAs(hostPath+java.io.File.separator+fileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		int resultCount=0;
		
		try {
			connection=JDBCUtil.getConnection();
			//id不需要插入 有数据库自增长实现
			preparedStatement=connection.prepareStatement("insert into auction (AUCTIONNAME,AUCTIONSTARTPRICE,AUCTIONUPSET,AUCTIONSTARTTIME,AUCTIONENDTIME,AUCTIONDESC,AUCTIONPICPATH,CREATETIME,UPDATETIME) values (?,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, "青龙偃月刀");
			//时间戳格式 年月日 -隔开 时分秒 ：隔开  最少精确到秒
			preparedStatement.setDouble(2, 1000d);
			preparedStatement.setDouble(3, 1000d);
			preparedStatement.setTimestamp(4, Timestamp.valueOf("2011-11-11 11:11:11"));
   
			preparedStatement.setTimestamp(5, Timestamp.valueOf("2011-11-11 11:11:11"));
   
			preparedStatement.setString(6, "关公专属");
			preparedStatement.setString(7, "");
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
   //增删改都是executeupdate
			resultCount=preparedStatement.executeUpdate();
			System.out.println(resultCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

}
