package com.qianfeng.biz;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.entity.Auction;

public interface AuctionBIZ {
 List<Auction>auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum);
 BigDecimal getAllcount();
 
 
 String auctionAdd(File userFile,String fileName,String hostPath,Auction auction);

 
 
 Auction auctionFindById(int auctionid);
 String auctionUpdate(ServletConfig config,HttpServletRequest request,HttpServletResponse response);
 int auctionDelByID(int auctionID);
 
 List<Auction>serchEndAuctionList();
 List<Auction>serchNotEndAuctionList();
 List<Auction>searchAuctionList(String auctionName,String auctionStartTime,
		 String auctionEndTime,String auctionStartPrice);
 
 
 
 
}

