package com.qianfeng.entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Auction entity. @author MyEclipse Persistence Tools
 */
public class Auction extends AbstractAuction implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Auction(){
		super();
	}
	
	public Auction(int auctionID){
		super(auctionID);
	}

	public Auction(int auctionID, String auctionName,
			Double auctionStartPrice, Double auctionUpset,
			Timestamp auctionStartTime, Timestamp auctionEndTime,
			String auctionDESC, String auctionPICPath, Timestamp createTime,
			Timestamp updateTime, Set auctionrecords){
		super(auctionID,auctionName,
				auctionStartPrice,auctionUpset,
				auctionStartTime,auctionEndTime,
				auctionDESC,auctionPICPath,createTime,
				updateTime, auctionrecords);
	}
	/** full constructor */
	

}
