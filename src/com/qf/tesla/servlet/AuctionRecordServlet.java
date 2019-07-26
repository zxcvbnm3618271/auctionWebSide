package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.biz.AuctionRecordBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.bizimpl.AuctionRecordBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.entity.AuctionRecord;

@Entity
public class AuctionRecordServlet extends HttpServlet {

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
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
		
		
		try {
		Auction auction = auctionBIZ.auctionFindById(Integer.parseInt(auctionId));
		List<AuctionRecord>  auctionRecords = auctionRecordBIZ.findAuctionRecordByAuctionId(Integer.parseInt(auctionId));
		request.setAttribute("record_list", auctionRecords);
		request.setAttribute("AuctionObj", auction);
			request.getRequestDispatcher("auctionDetail.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
