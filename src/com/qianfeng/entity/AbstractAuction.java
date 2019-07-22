package com.qianfeng.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAuction entity provides the base persistence definition of the
 * Auction entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAuction implements java.io.Serializable {

	// Fields

	private int auctionID;
	private String auctionName;
	private double auctionStartPrice;
	private double auctionUpset;
	private Timestamp auctionStartTime;
	private Timestamp auctionEndTime;
	private String auctionDESC;
	private String auctionPICPath;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Set auctionrecords = new HashSet(0);
	public int getAuctionID() {
		return auctionID;
	}

	public AbstractAuction(int auctionID) {
		this.auctionID = auctionID;
	}

	/** default constructor */
	public AbstractAuction() {
	}

	public AbstractAuction(int auctionID, String auctionName,
			double auctionStartPrice, double auctionUpset,
			Timestamp auctionStartTime, Timestamp auctionEndTime,
			String auctionDESC, String auctionPICPath, Timestamp createTime,
			Timestamp updateTime, Set auctionrecords) {
		this.auctionID = auctionID;
		this.auctionName = auctionName;
		this.auctionStartPrice = auctionStartPrice;
		this.auctionUpset = auctionUpset;
		this.auctionStartTime = auctionStartTime;
		this.auctionEndTime = auctionEndTime;
		this.auctionDESC = auctionDESC;
		this.auctionPICPath = auctionPICPath;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.auctionrecords = auctionrecords;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}



	public String getAuctionName() {
		return auctionName;
	}



	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}



	public double getAuctionStartPrice() {
		return auctionStartPrice;
	}



	public void setAuctionStartPrice(double auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}



	public double getAuctionUpset() {
		return auctionUpset;
	}



	public void setAuctionUpset(double auctionUpset) {
		this.auctionUpset = auctionUpset;
	}



	public Timestamp getAuctionStartTime() {
		return auctionStartTime;
	}



	public void setAuctionStartTime(Timestamp auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}



	public Timestamp getAuctionEndTime() {
		return auctionEndTime;
	}



	public void setAuctionEndTime(Timestamp auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}



	public String getAuctionDESC() {
		return auctionDESC;
	}



	public void setAuctionDESC(String auctionDESC) {
		this.auctionDESC = auctionDESC;
	}



	public String getAuctionPICPath() {
		return auctionPICPath;
	}



	public void setAuctionPICPath(String auctionPICPath) {
		this.auctionPICPath = auctionPICPath;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}



	public Timestamp getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}



	// Constructors

	

	

	public Set getAuctionrecords() {
		return this.auctionrecords;
	}

	public void setAuctionrecords(Set auctionrecords) {
		this.auctionrecords = auctionrecords;
	}



	

	
}