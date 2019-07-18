package com.qianfeng.entity;

import java.sql.Timestamp;

public class AuctionRecord {

	private int id;
	//ORM思想中 是没有主外键 只有关联的概念 比如
	//双向的1对1 单向的1对多 单向的多对1 双向的一对多 和单向的多对多
	//一个用户可以竞拍多个拍卖品 所以对于拍卖品记录表而言 它和AUCTIONUSER是一个多(AuctionRecord)对一(AuctionUser)的关系
	//单向的多(第一个字指的是当前的实体类)对1(声明的属性)
	private AuctionUser auctionUser;
	private Auction auctionID;
	private Timestamp auctionTime;
	private double auctionPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AuctionUser getAuctionUser() {
		return auctionUser;
	}
	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}
	public Auction getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(Auction auctionID) {
		this.auctionID = auctionID;
	}
	public Timestamp getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(Timestamp auctionTime) {
		this.auctionTime = auctionTime;
	}
	public double getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	@Override
	public String toString() {
		return "AuctionRecord [id=" + id + ", auctionUser=" + auctionUser
				+ ", auctionID=" + auctionID + ", auctionTime=" + auctionTime
				+ ", auctionPrice=" + auctionPrice + "]";
	}
	
	
	
	
	
}
