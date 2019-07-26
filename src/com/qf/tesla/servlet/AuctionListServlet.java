package com.qf.tesla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.enums.AuctionLoginStateEnum;
import com.qf.tesla.enums.AuctionStateEnum;
import com.qf.tesla.util.StringUtil;
import com.qf.tesla.vo.PageVO;
import com.sun.faces.el.ELConstants;

@Entity
public class AuctionListServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

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
		
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();

		// 显示拍卖品
		String state = request.getParameter("msg");
		String pageIndex = request.getParameter("pageIndex");
		BigDecimal pageIndexBigDecimal = null;
		String pageNum = "5";
		if(StringUtil.notEmpty(request.getParameter("pageNum"))){
			pageNum = request.getParameter("pageNum");
		}

		BigDecimal total = null;
		BigDecimal endPage = null;
		try {
			total = auctionBIZ.getAllcount();
			endPage = total.divide(new BigDecimal(pageNum), 0,
					BigDecimal.ROUND_UP);

			if (state != null) {
				if (state.equals(AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS
								.getValue())) {
					// 如果用户是登入到这里，则页数是第一页
					pageIndex = "1";
					pageIndexBigDecimal = new BigDecimal(pageIndex);
				} else if (state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
								.getVaule())) {
					pageIndexBigDecimal = endPage;
				} else if (state.equals(AuctionStateEnum.AUCTION_ADD_FAIL
								.getVaule())) {
					try {
						request.getRequestDispatcher("error.jsp").forward(request,
								response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("添加失败！");
					}

				} else if (state.equals(AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getVaule())) {
					System.out.println(pageIndex);
					pageIndexBigDecimal = new BigDecimal(pageIndex);
				}
			} else {
				pageIndexBigDecimal = new BigDecimal(pageIndex);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			log("这里有错");
		}

		List<Auction> auctionlist = null;
		try {
			auctionlist = auctionBIZ.auctonListByPage(
					pageIndexBigDecimal, new BigDecimal(pageNum));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		 
		  logger.error(e1.getMessage());
			
		}

		// 实例化pageVO
		PageVO<Auction> pageVO = new PageVO<Auction>();
		pageVO.setLists(auctionlist);
		pageVO.setPageIndex(pageIndexBigDecimal);
		pageVO.setPageNum(new BigDecimal(pageNum));
		pageVO.setTotal(total);
		pageVO.setEndPage(endPage);
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
