package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.vo.PageVO;

@Entity
public class AuctionSearchServlet extends HttpServlet {

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
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String auctionStartTime = request.getParameter("auctionStartTime");
		String auctionEndTime = request.getParameter("auctionEndTime");
		String auctionStartPrice = request.getParameter("auctionStartPrice");
		String auctionName = request.getParameter("auctionName");
		
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> aList = null;
		try {
			aList = auctionBIZ.searchAuctionList(auctionName, auctionStartTime, auctionEndTime, auctionStartPrice);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PageVO<Auction> pageVO = new PageVO<Auction>();
		pageVO.setLists(aList);
		pageVO.setPageIndex(new BigDecimal(1));
		pageVO.setPageNum(new BigDecimal(999));
		pageVO.setTotal(new BigDecimal(999));
		pageVO.setEndPage(new BigDecimal(1));
		
		request.setAttribute("auctionPageVO", pageVO);
		try {
			request.getRequestDispatcher("auctionList.jsp").forward(request,
					response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
