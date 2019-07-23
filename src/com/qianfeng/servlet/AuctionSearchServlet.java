package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.vo.PageVO;

public class AuctionSearchServlet extends HttpServlet {

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

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String auctionName=request.getParameter("auctionName");
		String auctionStartTime=request.getParameter("auctionStartTime");
		String auctionEndTime=request.getParameter("auctionEndTime");
		String auctionStartPrice=request.getParameter("auctionStartPrice");
		
		AuctionBIZ auctionBIZ=new AuctionBIZImpl();
		
		
		try {
			List<Auction> auctionList=auctionBIZ.searchAuctionList(auctionName, auctionStartTime, auctionEndTime, auctionStartPrice);
			PageVO<Auction> pageVO=new PageVO();
			
			pageVO.setLists(auctionList);
			pageVO.setPageIndex(new BigDecimal(1));
			pageVO.setPageNum(new BigDecimal(5));
			pageVO.setTotal(new BigDecimal(99999));
			pageVO.setEndPage(new BigDecimal(1));
			
			request.setAttribute("auctionPageInfo", pageVO);
			request.getRequestDispatcher("auctionList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
