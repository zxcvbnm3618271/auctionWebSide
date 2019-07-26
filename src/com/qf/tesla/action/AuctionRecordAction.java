package com.qf.tesla.action;

import java.util.ArrayList;
import java.util.List;

import sun.text.resources.FormatData;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.biz.AuctionRecordBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.bizimpl.AuctionRecordBIZImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.entity.AuctionRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionRecordAction extends ActionSupport implements ModelDriven<AuctionRecord>{

	private AuctionRecord auctionRecord  = new AuctionRecord();
	AuctionRecordBIZ auctionRecordBIZ;
	AuctionBIZ auctionBIZ = new AuctionBIZImpl();
	private Auction auctionObj = new Auction();
	private int auctionId ;
	private int pageIndex;
	private String msg;
	private List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
	
	
	public String addAuctionRecord(){
		
		int resultCount = auctionRecordBIZ.auctionRecordAdd(auctionRecord);
		if (resultCount>0) {
			msg="success";
		}else{
			msg="input";
		}
		return SUCCESS;
	}
	
	public String auctionDetail(){
		try {
			auctionObj = auctionBIZ.auctionFindById(auctionId);
			auctionRecords = auctionRecordBIZ.findAuctionRecordByAuctionId(auctionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<AuctionRecord> getAuctionRecords() {
		return auctionRecords;
	}

	public void setAuctionRecords(List<AuctionRecord> auctionRecords) {
		this.auctionRecords = auctionRecords;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AuctionRecordBIZ getAuctionRecordBIZ() {
		return auctionRecordBIZ;
	}

	public void setAuctionRecordBIZ(AuctionRecordBIZ auctionRecordBIZ) {
		this.auctionRecordBIZ = auctionRecordBIZ;
	}

	public AuctionBIZ getAuctionBIZ() {
		return auctionBIZ;
	}

	public void setAuctionBIZ(AuctionBIZ auctionBIZ) {
		this.auctionBIZ = auctionBIZ;
	}
	

}
