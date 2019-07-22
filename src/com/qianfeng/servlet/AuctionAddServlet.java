package com.qianfeng.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.qianfeng.biz.Impl.AuctionBIZImpl;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		String auctionState;
		auctionState = auctionBIZ.auctionAdd(getServletConfig(), req, resp);
		try {
			req.getRequestDispatcher(
					"AuctionListServlet?msg=" + auctionState + "").forward(req,
					resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

	}

}
