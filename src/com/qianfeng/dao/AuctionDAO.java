package com.qianfeng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.entity.Auction;

public interface AuctionDAO {

	List<Auction> auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum);
	BigDecimal getAllcount();
	int auctionAdd(Auction auction)throws Exception;
	Auction auctionFindById(int auctionId);
	int auctionUpdate(Auction auction)throws Exception;

	int auctionDelByID(int auctionID);
	 List<Auction>serchEndAuctionList();
	 List<Auction>serchNotEndAuctionList();
	 List<Auction>searchAuctionList(String hql,Auction auction);
}
