package com.qf.tesla.entity;

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
     private Date auctionTime;
     private Integer auctionPrice;


    // Constructors

    /** default constructor */
    public AuctionRecord() {
    }


	public AuctionRecord(Integer id) {
		this.id = id;
	}


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


	public Date getAuctionTime() {
		return auctionTime;
	}


	public void setAuctionTime(Date auctionTime) {
		this.auctionTime = auctionTime;
	}


	public Integer getAuctionPrice() {
		return auctionPrice;
	}


	public void setAuctionPrice(Integer auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

    
    /** full constructor */
  

   
    // Property accessors

   
   








}