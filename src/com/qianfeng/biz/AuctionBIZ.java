package com.qianfeng.biz;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.entity.Auction;

public interface AuctionBIZ {
	/**
	 * @Descript
	 * @param pageIndex
	 * @param pageNum
	 * @return
	 */
	List<Auction> auctionListByPage(BigDecimal pageIndex, BigDecimal pageNum);

	/**
	 * 
	 * @return
	 */

	BigDecimal getAllCount();

	String auctionAdd(ServletConfig config, HttpServletRequest request,
			HttpServletResponse response);

	Auction auctionFindById(int auctionId);

	String auctionUpdate(ServletConfig config, HttpServletRequest request,
			HttpServletResponse response);

	int auctionDelByID(int auctionid);

	List<Auction> searchEndAuctionList();

	List<Auction> searchNotEndAuctionList();

	List<Auction> searchAuctionList(String auctionName,
			String auctionStartTime,String auctionEndTime, String auctionStartPrice);
}
