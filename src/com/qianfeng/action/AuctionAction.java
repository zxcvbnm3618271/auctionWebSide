package com.qianfeng.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.vo.PageVO;
//有了这个接口 用户直接传值就行
public class AuctionAction extends ActionSupport implements ModelDriven<Auction>{
	private String msg;
	private String pageIndex;
	private String pageNum="5";
	private boolean delete;
	
	private PageVO<Auction> auctionPageInfo=new PageVO<Auction>();

	private Auction auction=new Auction();
	
	AuctionBIZ auctionBIZ=new AuctionBIZImpl();
	private File userFile;
	private String userFileFileName;
	
	public String auctionListByPage() {

		if (msg != null) {
			if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS
					.getValue())) {
				pageIndex = "1";
			}
		}
		AuctionBIZ auctionBIZ=new AuctionBIZImpl();
		BigDecimal totalCount =auctionBIZ.getAllcount();
		BigDecimal pageIndex2=null;
		if (pageIndex!= null) {
			pageIndex2=new BigDecimal(pageIndex);
		}
		BigDecimal endPage=totalCount.divide(new BigDecimal(pageNum),0,BigDecimal.ROUND_UP);
		if (msg!=null) {
			if (msg.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue())) {
				pageIndex2=endPage;
			}
		}
		List<Auction> auctionList;
		try {
			auctionList=auctionBIZ.auctionListByPage(pageIndex2, new BigDecimal(pageNum));
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
	
	public String del(){
		int resultCount=auctionBIZ.auctionDelByID(getModel().getAuctionID());
	if (resultCount>0) {
		delete=true;
	}
	  return SUCCESS;
		
	}
	
	
	public String auctionAdd(){
		
		msg=null;
		try {
			msg=auctionBIZ.auctionAdd(userFile, userFileFileName,
					ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("upload"),auction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
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
