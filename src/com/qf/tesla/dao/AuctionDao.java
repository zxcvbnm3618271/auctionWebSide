package com.qf.tesla.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qf.tesla.entity.Auction;

public interface AuctionDao {
	List<Auction> auctonListByPage(BigDecimal pageIndex, BigDecimal pageNum);

	BigDecimal getAllcount();

	int auctionAdd(Auction auction) throws Exception;

	int auctionUpdate(Auction auction) throws Exception;

	Auction auctionFindById(int auctionId);

	int deleteById(int auctionId);

	List<Auction> searchNotEndAuctionList();

	List<Auction> searchEndAuctionList();
	
	List<Auction> searchAuctionList(String hql,Auction auction);
}
