package com.qianfeng.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.qianfeng.entity.Auction;
import com.qianfeng.entity.AuctionUser;

/**
 * Auctionrecord entity. @author MyEclipse Persistence Tools
 */
public class AuctionRecord implements java.io.Serializable {

	private Integer id;
	private AuctionUser auctionUser;
	private Auction auction;
	private Date auctionTime;
	private Integer auctionPrice;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AuctionUser getAuctionUser() {
		return auctionUser;
	}
	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
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
	
	


	

}
