package com.qianfeng.biz.Impl;

import java.util.List;

import com.qianfeng.biz.AuctionRecordBIZ;
import com.qianfeng.dao.AuctionRecordDAO;
import com.qianfeng.dao.Impl.AuctionRecordDAOImpl;
import com.qianfeng.entity.AuctionRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionRecordBIZImpl implements AuctionRecordBIZ {

	AuctionRecordDAO auctionRecordDAO=new AuctionRecordDAOImpl();
	@Override
	public List<AuctionRecord> findAuctionRecordByAuctionId(int auctionid) {
		// TODO Auto-generated method stub
		return auctionRecordDAO.findAuctionRecordByAuctionId(auctionid);
	}

}
