package com.qianfeng.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionStateEnum;

public interface AuctionBIZ {

	 List<Auction>auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum);
		
	  BigDecimal getAllcount();
	
	 String auctionAdd(java.io.File userFile,String fileName,String hostPath,Auction auction);

 Auction auctionFindById(int auctionId);

int auctionDelByID(int auctionID);	
	
	 List<Auction> serchEndAuctionList();
		

 List<Auction> serchNotEndAuctionList();	
	
	List<Auction> searchAuctionList(String auctionName,
			String auctionStartTime, String auctionEndTime,
			String auctionStartPrice);
		
	String auctionUpdate(java.io.File userFile, String fileName,
			String hostPath, Auction auction, String beforeFileName);
				
	

}
