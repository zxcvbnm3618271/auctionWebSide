package com.qianfeng.biz;

import java.util.List;

import com.qianfeng.entity.AuctionRecord;

public interface AuctionRecordBIZ {

	List<AuctionRecord> findAuctionRecordByAuctionId(int auctionid);
	int auctionRecordAdd(AuctionRecord auctionRecord);
	
}
