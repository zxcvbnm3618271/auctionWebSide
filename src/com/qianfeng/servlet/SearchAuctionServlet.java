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
import com.qianfeng.biz.Impl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.vo.pageVO;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class SearchAuctionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchAuctionServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
			{
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			String auctionName= request.getParameter("auctionName");
			String auctionStartTime=request.getParameter("auctionStartTime");
			String auctionEndTime = request.getParameter("auctionEndTime");
			 String auctionStartPrice = request.getParameter("auctionStartPrice");
			 AuctionBIZ auctionBIZ=new AuctionBIZImpl();
			 List<Auction> auctionList=auctionBIZ.searchAuctionList(auctionName, auctionStartTime, auctionEndTime,auctionStartPrice);
			pageVO<Auction> pageVO=new pageVO<Auction>();
			pageVO.setLists(auctionList);
			pageVO.setPageIndex(new BigDecimal(1));
			pageVO.setPageNum(new BigDecimal(5));
			pageVO.setTotal(new BigDecimal(auctionList.size()));
			BigDecimal endPage = new BigDecimal(auctionList.size()).divide(new BigDecimal(5), 0,
					BigDecimal.ROUND_UP);
			pageVO.setEndPage(endPage);
			request.setAttribute("auctionPageInfo", pageVO);
			 try {
				request.getRequestDispatcher("auctionList.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
