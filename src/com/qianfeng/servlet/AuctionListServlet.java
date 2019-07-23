package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.StringUtil;
import com.qianfeng.vo.PageVO;

public class AuctionListServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//显示拍卖品
		//获取用户操作状态
		
		String state=request.getParameter("msg");
		//用户点击分页会接收到的参数
		String pageIndex=request.getParameter("pageIndex");
		//每页显示的条数
		String pageNum="5";
		
		if (StringUtil.notEmpty(request.getParameter("pageNum"))) {
			pageNum=request.getParameter("pageNum");
		}
		if (state!=null) {
			if(state.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS.getValue())||state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue())){
				//登录进入 当前页码1
				pageIndex="1";
			}
		}
		
		//查询拍卖品数据
		AuctionBIZ auctionBIZ=new AuctionBIZImpl();
		//选中alt +方向键  移动代码块
		try {
			//查询拍卖品总条数
            BigDecimal totalcount=auctionBIZ.getAllcount();
			
            BigDecimal pageIndex2=null;
			if (pageIndex!=null) {
				pageIndex2=new BigDecimal(pageIndex);
			}
			//尾页等于总条数除每页显示的条数divide后面的三个参数分别是被除数和取正和四舍五入
			BigDecimal endPage=totalcount.divide(new BigDecimal(pageNum),0,BigDecimal.ROUND_UP);
			
			
			
			
		
			
			if (state!=null) {
				if (state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue())) {
					pageIndex2=endPage;
				}
			}
			List<Auction>auctionList=auctionBIZ.auctionListByPage(pageIndex2, new BigDecimal(pageNum));
			//实例化pageVO
			PageVO<Auction>pageVO=new PageVO<Auction>();
			pageVO.setLists(auctionList);
			//如果用户添加成功操作进来 那pageindex就应该等于尾页
			pageVO.setPageIndex(pageIndex2);
			pageVO.setPageNum(new BigDecimal(pageNum));
			pageVO.setTotal(totalcount);
			
			
		
			pageVO.setEndPage(endPage);
			//将pagevo存入到请求体中
			request.setAttribute("auctionPageInfo", pageVO);
			//再将请求体和响应体转发到分页前端文件中
			request.getRequestDispatcher("auctionList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
