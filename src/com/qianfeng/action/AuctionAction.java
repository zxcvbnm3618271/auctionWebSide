package com.qianfeng.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.vo.PageVO;


//��������ӿ� �û�ֱ�Ӵ�ֵ����
public class AuctionAction extends ActionSupport implements
		ModelDriven<Auction> {
	private String msg;
	private String pageIndex;
	private String pageNum = "5";
	private boolean delete;

	private PageVO<Auction> auctionPageInfo = new PageVO<Auction>();

	private Auction auction=new Auction();

	private String beforeFileName;

	public Map<String, Object> map = new HashMap<String, Object>();

	AuctionBIZ auctionBIZ ;
	private File userFile;
	private String userFileFileName;
	
	
	

	public AuctionBIZ getAuctionBIZ() {
		return auctionBIZ;
	}

	public void setAuctionBIZ(AuctionBIZ auctionBIZ) {
		this.auctionBIZ = auctionBIZ;
	}

	public String auctionListByPage() {

		if (msg != null) {
			if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS
					.getValue())) {
				pageIndex = "1";
			}
		}
		//AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		BigDecimal totalCount = auctionBIZ.getAllcount();
		BigDecimal pageIndex2 = null;
		if (pageIndex != null) {
			pageIndex2 = new BigDecimal(pageIndex);
		}
		BigDecimal endPage = totalCount.divide(new BigDecimal(pageNum), 0,
				BigDecimal.ROUND_UP);
		if (msg != null) {
			if (msg.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue())) {
				pageIndex2 = endPage;
			}
		}
		List<Auction> auctionList;
		try {
			auctionList = auctionBIZ.auctionListByPage(pageIndex2,
					new BigDecimal(pageNum));
			auctionPageInfo.setLists(auctionList);
			auctionPageInfo.setPageIndex(pageIndex2);
			auctionPageInfo.setPageNum(new BigDecimal(pageNum));
			auctionPageInfo.setTotal(totalCount);
			auctionPageInfo.setEndPage(endPage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String del() {
		int resultCount = auctionBIZ.auctionDelByID(getModel().getAuctionID());
		if (resultCount > 0) {
			delete = true;
		}
		return SUCCESS;

	}

	public String auctionAdd() {

		msg = null;
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

	public String auctionUpdate() {

		msg = AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
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

	public String findAuctionById() {
		try {
			auction = auctionBIZ.auctionFindById(getModel().getAuctionID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String searchAuctionList() {

		List<Auction> auctions = auctionBIZ.searchAuctionList(auction
				.getAuctionName(), auction.getAuctionStartTime() == null ? null
				: auction.getAuctionStartTime().toString(), auction
				.getAuctionEndTime() == null ? null : auction
				.getAuctionEndTime().toString(),
				auction.getAuctionStartPrice() == null ? null : auction
						.getAuctionStartPrice().toString());
		auctionPageInfo.setLists(auctions);
		auctionPageInfo.setEndPage(new BigDecimal(1));
		auctionPageInfo.setPageNum(new BigDecimal(5));
		auctionPageInfo.setPageIndex(new BigDecimal(1));
		auctionPageInfo.setTotal(new BigDecimal(99999));
		return SUCCESS;
	}

	

	public String auctionResult() {
		List<Auction> endAuctions = auctionBIZ.serchEndAuctionList();
		List<Auction> notEndAuctions = auctionBIZ.serchNotEndAuctionList();

		map.put("end", endAuctions);
		map.put("notend", notEndAuctions);
		return SUCCESS;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getBeforeFileName() {
		return beforeFileName;
	}

	public void setBeforeFileName(String beforeFileName) {
		this.beforeFileName = beforeFileName;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
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

	public PageVO<Auction> getAuctionPageInfo() {
		return auctionPageInfo;
	}

	public void setAuctionPageInfo(PageVO<Auction> auctionPageInfo) {
		this.auctionPageInfo = auctionPageInfo;
	}

	@Override
	public Auction getModel() {
		// TODO Auto-generated method stub
		return auction;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}
