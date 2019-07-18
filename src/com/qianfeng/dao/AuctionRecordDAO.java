package com.qianfeng.dao;

import java.util.List;

import com.qianfeng.entity.AuctionRecord;

public interface AuctionRecordDAO {

	List<AuctionRecord> findAuctionRecordByAuctionId(int auctionid);
	int auctionRecordAdd(AuctionRecord auctionRecord);
}
