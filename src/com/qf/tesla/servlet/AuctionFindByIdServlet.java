package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.entity.Auction;

@Entity
public class AuctionFindByIdServlet extends HttpServlet {

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

		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		String auctionId = request.getParameter("auctionid");
		try {
			Auction auction = auctionBIZ.auctionFindById(Integer
					.parseInt(auctionId));
			request.setAttribute("auction", auction);

			request.getRequestDispatcher("addAuction.jsp?auctionId="+auctionId).forward(request,
					response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
