package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionRecordBIZ;
import com.qianfeng.biz.Impl.AuctionRecordBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionRecord;
import com.qianfeng.entity.AuctionUser;

public class AuctionRecordAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionRecordAddServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionPrice = request.getParameter("auctionPrice");
		String auctionID = request.getParameter("auctionid");
		String userID = request.getParameter("userid");
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();

		try {
			AuctionRecord auctionRecord = new AuctionRecord();
			auctionRecord.setAuctionPrice(Double.parseDouble(auctionPrice));
			auctionRecord.setAuctionTime(new Timestamp(System
					.currentTimeMillis()));
			auctionRecord.setAuctionUser(new AuctionUser(Integer
					.parseInt(userID)));
			auctionRecord.setAuction(new Auction(Integer.parseInt(auctionID)));

			int resultCount = auctionRecordBIZ.auctionRecordAdd(auctionRecord);
			if (resultCount > 0) {
				try {
					request.getRequestDispatcher(
							"AuctionRecordServlet?auctionId=" + auctionID
									+ "&msg=success")
							.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					request.getRequestDispatcher(
							"AuctionRecordServlet?auctionId=" + auctionID
									+ "&msg=error").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NumberFormatException e) {
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

}
