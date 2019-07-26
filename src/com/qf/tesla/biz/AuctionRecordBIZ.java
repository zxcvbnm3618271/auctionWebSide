package com.qf.tesla.biz;

import java.util.List;

import javax.ejb.FinderException;

import com.qf.tesla.entity.AuctionRecord;

public interface AuctionRecordBIZ {
	List<AuctionRecord> findAuctionRecordByAuctionId(int auctionId);
	
	int auctionRecordAdd(AuctionRecord auctionRecord);
}
