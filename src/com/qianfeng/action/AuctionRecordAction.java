package com.qianfeng.action;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.biz.AuctionRecordBIZ;
import com.qianfeng.bizimpl.AuctionBIZImpl;
import com.qianfeng.bizimpl.AuctionRecordBIZImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionRecord;

public class AuctionRecordAction extends ActionSupport implements
 ModelDriven<AuctionRecord>{
	
	private AuctionRecord auctionRecord=new AuctionRecord();
	
	private Auction auctionObj; 
	private List<AuctionRecord> record_list;
	
	AuctionBIZ auctionBIZ;
	AuctionRecordBIZ auctionRecordBIZ;

	private int pageIndex;
	private int auctionID;
	private String msg;
	
	
	
	
	public AuctionBIZ getAuctionBIZ() {
		return auctionBIZ;
	}


	public void setAuctionBIZ(AuctionBIZ auctionBIZ) {
		this.auctionBIZ = auctionBIZ;
	}


	public AuctionRecordBIZ getAuctionRecordBIZ() {
		return auctionRecordBIZ;
	}


	public void setAuctionRecordBIZ(AuctionRecordBIZ auctionRecordBIZ) {
		this.auctionRecordBIZ = auctionRecordBIZ;
	}


	public String auctionRecordAdd(){
		try {
		int result=	auctionRecordBIZ.auctionRecordAdd(auctionRecord);
		if (result>0) {
			msg="success";
		}else {
			msg="error";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	
	public String auctionDetail(){
		auctionObj=auctionBIZ.auctionFindById(auctionID);
		record_list=auctionRecordBIZ.findAuctionRecordByAuctionId(auctionID);
		return SUCCESS;
	}
	@Override
	public AuctionRecord getModel() {
		// TODO Auto-generated method stub
		return auctionRecord;
	}


	public AuctionRecord getAuctionRecord() {
		return auctionRecord;
	}


	public void setAuctionRecord(AuctionRecord auctionRecord) {
		this.auctionRecord = auctionRecord;
	}


	public Auction getAuctionObj() {
		return auctionObj;
	}


	public void setAuctionObj(Auction auctionObj) {
		this.auctionObj = auctionObj;
	}


	public List<AuctionRecord> getRecord_list() {
		return record_list;
	}


	public void setRecord_list(List<AuctionRecord> record_list) {
		this.record_list = record_list;
	}


	public int getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	public int getAuctionID() {
		return auctionID;
	}


	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	
	

}
