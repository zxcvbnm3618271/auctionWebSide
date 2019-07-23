package com.qianfeng.bizimpl;

import java.util.List;

import com.qianfeng.biz.AuctionRecordBIZ;
import com.qianfeng.dao.AuctionRecordDAO;
import com.qianfeng.daoimpl.AuctionRecordDAOImpl;
import com.qianfeng.entity.AuctionRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionRecordBIZImpl implements AuctionRecordBIZ {

	AuctionRecordDAO auctionRecordDAO=new AuctionRecordDAOImpl();
	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionID) {
		// TODO Auto-generated method stub
		return auctionRecordDAO.findAuctionRecordByAuctionId(auctionID);
	}
	@Override
	public int auctionRecordAdd(AuctionRecord auctionRecord) {
		// TODO Auto-generated method stub
		return auctionRecordDAO.auctionRecordAdd(auctionRecord);
	}

}
