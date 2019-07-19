package com.qianfeng.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.biz.Impl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.StringUtil;
import com.qianfeng.vo.pageVO;

public class AuctionListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// 进入到这里 把拍卖品显示出来
			// 获取到用户的操作状态
			String state = req.getParameter("msg");
			// 用户点击分页会接收到的参数
			String pageIndex = req.getParameter("pageIndex");
			// 每页显示的条数
			String pageNum = "5";
			// 如果又传过来pagenum的数量,读取
			if (StringUtil.notEmpty(req.getParameter("pageNum"))) {
				pageNum = req.getParameter("pageNum");
			}
			// 如果用户是分页操作进入到这个文件中 state为null
			if (state != null) {
				// 如果用户是登录 进入到这个文件 那么当前的页码是1
				if (state.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS
						.getValue())
						|| state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
								.getValue())) {
					pageIndex = "1";
				}
			}
			// 查询出拍卖品的数据
			AuctionBIZ auctionBIZ = new AuctionBIZImpl();
				// 查询出拍卖品的总条数
				BigDecimal totalCount = auctionBIZ.getAllCount();
				BigDecimal pageIndex2 = new BigDecimal(pageIndex);
				// if (pageIndex!=null) {
				// if
				// (state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue())) {
				//
				// }
				// }
				BigDecimal endPage = totalCount.divide(new BigDecimal(pageNum), 0,
						BigDecimal.ROUND_UP);
				// 如果用户是添加成功的操作进来 那么pageindex就应该等于尾页
				if (state != null) {
					if (state.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
							.getValue())) {
						pageIndex2 = endPage;
					}
				}
				List<Auction> auctionList = auctionBIZ.auctionListByPage(
						pageIndex2, new BigDecimal(pageNum));
				pageVO<Auction> pageVO = new pageVO<Auction>();

				pageVO.setLists(auctionList);
				pageVO.setPageIndex(pageIndex2);
				pageVO.setPageNum(new BigDecimal(pageNum));
				pageVO.setTotal(totalCount);
				// 尾页等于总条数除 每页显示的条数 divide 后面的三个参数 分别是 被除数 取正 四舍五入
				pageVO.setEndPage(endPage);
				// auctionBIZ.auctionListByPage(new BigDecimal(pageIndex), new
				// BigDecimal(
				// pageNum));
				// jiangpagevo存入请求体中
				req.setAttribute("auctionPageInfo", pageVO);
		} catch (Exception e) {
				try {
					try {
						req.getRequestDispatcher("404.html").forward(req, resp);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ServletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			try {
			
				req.getRequestDispatcher("auctionList.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
