package com.qf.tesla.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.enums.AuctionLoginStateEnum;
import com.qf.tesla.enums.AuctionStateEnum;
import com.qf.tesla.util.StringUtil;
import com.qf.tesla.vo.PageVO;
import com.sun.org.apache.bcel.internal.generic.NEW;

import freemarker.core.ReturnInstruction.Return;

public class AuctionAction extends ActionSupport implements
		ModelDriven<Auction> {
	// 如果没有实现这个接口，用户必须这样传值value=Auction.auctionId,不能直接传属性名
	String msg;
	String pageIndex = "1";
	String pageNum = "5";
	PageVO<Auction> auctionPageVO = new PageVO<Auction>();
	//
	private Boolean delete;
	private Auction auction = new Auction();
	AuctionBIZ auctionBIZ ;
	private File userFile;
	private String userFileFileName;
	private String beforeFileName;
	private Map<String, Object> map = new HashMap<String, Object>();

	// 文件名必须是userFileFileName 来接受文件，fileUpload拦截器来帮我们实现

	public String auctionListByPage() {

		BigDecimal pageIndexBigDecimal = null;
		BigDecimal total = null;
		BigDecimal endPage = null;

		total = auctionBIZ.getAllcount();
		endPage = total.divide(new BigDecimal(pageNum), 0, BigDecimal.ROUND_UP);
		try {
			if (msg != null) {
				if (msg.equals(AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS
						.getValue())) {
					// 如果用户是登入到这里，则页数是第一页
					pageIndex = "1";
					pageIndexBigDecimal = new BigDecimal(pageIndex);
				} else if (msg.equals(AuctionLoginStateEnum.AUCTION_lOGIN_FAIL
						.getValue())) {
					return INPUT;
				} else if (msg.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
						.getVaule())) {
					pageIndexBigDecimal = endPage;
				} else if (msg.equals(AuctionStateEnum.AUCTION_ADD_FAIL
						.getVaule())) {
					return INPUT;

				} else if (msg.equals(AuctionStateEnum.AUCTION_UPDATE_SUCCESS
						.getVaule())) {
					System.out.println(pageIndex);
					pageIndexBigDecimal = new BigDecimal(pageIndex);
				}
			} else {
				pageIndexBigDecimal = new BigDecimal(pageIndex);
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

		}

		List<Auction> auctionlist = null;
		try {
			auctionlist = auctionBIZ.auctonListByPage(pageIndexBigDecimal,
					new BigDecimal(pageNum));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}

		// 实例化pageVO
		auctionPageVO.setLists(auctionlist);
		auctionPageVO.setPageIndex(pageIndexBigDecimal);
		auctionPageVO.setPageNum(new BigDecimal(pageNum));
		auctionPageVO.setTotal(total);
		auctionPageVO.setEndPage(endPage);

		return SUCCESS;
	}

	public void delete() {
		int resultCount = 0;
		try {
			resultCount = auctionBIZ.deleteById(getModel().getAuctionId());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (resultCount > 0) {
			try {
				ServletActionContext.getResponse().getWriter().print(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String findAuctionById() {
		auction = auctionBIZ.auctionFindById(getModel().getAuctionId());
		return SUCCESS;
	}

	public String add() {
		msg = AuctionStateEnum.AUCTION_ADD_FAIL.getVaule();
		try {
			msg = auctionBIZ
					.auctionAdd(userFile, userFileFileName,
							ServletActionContext.getRequest().getSession()
									.getServletContext().getRealPath("upload"),
							auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String AuctionAUpdate() {
		msg = AuctionStateEnum.AUCTION_UPDATE_FAIL.getVaule();
		try {
			msg = auctionBIZ.auctionUpdate(userFile, userFileFileName,
					ServletActionContext.getRequest().getSession()
							.getServletContext().getRealPath("upload"),
					auction, beforeFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String searchAuctionList() {
		List<Auction> auctionlist = null;
		try {
			auctionlist = auctionBIZ.searchAuctionList(
					auction.getAuctionName(),
					auction.getAuctionStartTime() == null ? null : auction
							.getAuctionStartTime().toString(), auction
							.getAuctionEndTime() == null ? null : auction
							.getAuctionEndTime().toString(), auction
							.getAuctionStartPrice() == null ? null : auction
							.getAuctionStartPrice().toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}

		// 实例化pageVO
		auctionPageVO.setLists(auctionlist);
		auctionPageVO.setPageIndex(new BigDecimal(1));
		auctionPageVO.setPageNum(new BigDecimal(5));
		auctionPageVO.setTotal(new BigDecimal(999));
		auctionPageVO.setEndPage(new BigDecimal(1));

		return SUCCESS;
	}

	public String auctionResult() {
		List<Auction> endAuctions = auctionBIZ.searchEndAuctionList();
		List<Auction> notEndAuctions = auctionBIZ.searchNotEndAuctionList();
		map.put("end", endAuctions);
		map.put("notEnd", notEndAuctions);
		return SUCCESS;
	}

	@Override
	public Auction getModel() {
		// TODO Auto-generated method stub
		return auction;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public PageVO<Auction> getAuctionPageVO() {
		return auctionPageVO;
	}

	public void setAuctionPageVO(PageVO<Auction> auctionPageVO) {
		this.auctionPageVO = auctionPageVO;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public String getBeforeFileName() {
		return beforeFileName;
	}

	public void setBeforeFileName(String beforeFileName) {
		this.beforeFileName = beforeFileName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public AuctionBIZ getAuctionBIZ() {
		return auctionBIZ;
	}

	public void setAuctionBIZ(AuctionBIZ auctionBIZ) {
		this.auctionBIZ = auctionBIZ;
	}

}
