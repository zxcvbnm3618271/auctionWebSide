package com.qf.tesla.dao;

import java.util.List;

import com.qf.tesla.entity.AuctionRecord;

public interface AuctionRecordDao {
	List<AuctionRecord> findAuctionRecordByAuctionId(int auctionId);
	
	int auctionRecordAdd(AuctionRecord auctionRecord);
}
