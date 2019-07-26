package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qf.tesla.biz.AuctionRecordBIZ;
import com.qf.tesla.bizimpl.AuctionRecordBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.entity.AuctionRecord;
import com.qf.tesla.entity.AuctionUser;
import com.sun.faces.el.ELConstants;

@Entity
public class AuctionGoodsServlet extends HttpServlet {

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
		Logger logger = Logger.getLogger(getClass());
		
		// 添加竞拍记录
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
		String auctionPrice = request.getParameter("auctionPrice");
		// auctionPrice.replace(""/","");
		String userId = request.getParameter("userId");
		String auctionId = request.getParameter("auctionId");
		AuctionRecord auctionRecord = new AuctionRecord();

		try {
			auctionRecord.setAuctionPrice(Integer.parseInt(auctionPrice));
			auctionRecord.setAuctionTime(new Timestamp(System
					.currentTimeMillis()));
			auctionRecord.setAuctionUser(new AuctionUser(Integer.parseInt(userId)));
			auctionRecord.setAuction(new Auction(Integer.parseInt(auctionId)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
			e1.printStackTrace();
			
		}

		try {
			if (auctionRecordBIZ.auctionRecordAdd(auctionRecord) > 0) {

				request.getRequestDispatcher(
						"AuctionRecordServlet?auctoinId=" + auctionId
								+ "&msg=success").forward(request, response);

			} else {
				request.getRequestDispatcher(
						"AuctionRecordServlet?auctoinId=" + auctionId
								+ "&msg=error").forward(request, response);

			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
