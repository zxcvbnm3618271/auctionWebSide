package com.qianfeng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.entity.Auction;

public interface AuctionDAO {
	List<Auction> AuctionListByPage(BigDecimal pageIndex, BigDecimal pageNum);

	BigDecimal getAllCount();

	int auctionAdd(Auction auction) throws Exception;

	Auction auctionFindById(int auctionid);

	int auctionUpdate(Auction auction) throws Exception;

	int auctionDelByID(int auctionid);

	List<Auction> searchEndAuctionList();

	List<Auction> searchNotEndAuctionList();
	
	List<Auction> searchAuctionList(String sql);
}
