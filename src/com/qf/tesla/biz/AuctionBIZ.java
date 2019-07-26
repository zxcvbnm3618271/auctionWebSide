package com.qf.tesla.biz;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qf.tesla.entity.Auction;


public interface AuctionBIZ {
	List<Auction> auctonListByPage(BigDecimal pageIndex,BigDecimal pageNum);
	
	BigDecimal getAllcount();
	
//	String auctionAdd(ServletConfig config,HttpServletRequest request,HttpServletResponse response);
	String auctionAdd(File userFile,String fileName,String hostPath,Auction auction);
	
	String auctionUpdate(File userFile,String fileName,String hostPath,Auction auction,String beforeFileName);
	
	Auction auctionFindById(int auctionId); 
	
	int deleteById(int auctionId); 
	
	List<Auction> searchNotEndAuctionList();
	
	List<Auction> searchEndAuctionList();
	
	List<Auction> searchAuctionList(String auctionName,String auctionStartTime,String auctionEndTime,String auctionStratPrice);
	
	
}
