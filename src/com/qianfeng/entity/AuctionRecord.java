package com.qianfeng.entity;

import java.sql.Timestamp;
import java.util.Date;


/**
 * Auctionrecord entity. @author MyEclipse Persistence Tools
 */

public class AuctionRecord  implements java.io.Serializable {


    // Fields    

     private Integer id;
 	private Auction auction;
    private AuctionUser auctionUser;
    private Timestamp auctionTime;
    private Integer auctionPrice;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Auction getAuction() {
		return auction;
	}


	public void setAuction(Auction auction) {
		this.auction = auction;
	}


	public AuctionUser getAuctionUser() {
		return auctionUser;
	}


	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}


	public Timestamp getAuctionTime() {
		return auctionTime;
	}


	public void setAuctionTime(Timestamp auctionTime) {
		this.auctionTime = auctionTime;
	}


	public Integer getAuctionPrice() {
		return auctionPrice;
	}


	public void setAuctionPrice(Integer d) {
		this.auctionPrice = d;
	}





    // Constructors

    /** default constructor */
    public AuctionRecord() {
    }


	public AuctionRecord(Integer id, Auction auction, AuctionUser auctionUser,
			Timestamp auctionTime, Integer auctionPrice) {
		super();
		this.id = id;
		this.auction = auction;
		this.auctionUser = auctionUser;
		this.auctionTime = auctionTime;
		this.auctionPrice = auctionPrice;
	}


	public void setAuctionPrice(double parseDouble) {
		// TODO Auto-generated method stub
		
	}

    
    /** full constructor */
   

   
    // Property accessors

   
 
}