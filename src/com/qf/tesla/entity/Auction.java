package com.qf.tesla.entity;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.cfg.Configuration;

/**
 * Auction entity. @author MyEclipse Persistence Tools
 */
public class Auction implements java.io.Serializable {

	// Constructors

	/** default constructor */
	private int auctionId;
	private String auctionName;
	private Double auctionStartPrice;
	private Double auctionUpset;
	private Timestamp auctionStartTime;
	private Timestamp auctionEndTime;
	private String auctionDESC;
	private String auctionPICPath;
	private Timestamp createTime;
	private Timestamp updateTime;

	public Auction() {
	}

	public Auction(int auctionId) {
		this.auctionId = auctionId;
	}
	

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public Double getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(Double auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public Double getAuctionUpset() {
		return auctionUpset;
	}

	public void setAuctionUpset(Double auctionUpset) {
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

	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", auctionName="
				+ auctionName + ", auctionStartPrice=" + auctionStartPrice
				+ ", auctionUpset=" + auctionUpset + ", auctionStartTime="
				+ auctionStartTime + ", auctionEndTime=" + auctionEndTime
				+ ", auctionDESC=" + auctionDESC + ", auctionPICPath="
				+ auctionPICPath + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

//	public static void main(String[] args) {
//		AuctionUser auctionUser = (AuctionUser) new Configuration().configure()
//				.buildSessionFactory().openSession().get(AuctionUser.class, 2);
//		for (AtRole atRole : auctionUser.getAtRoles()) {
//			System.out.println(atRole.getDesc());
//			for(AtPermission atPermission : atRole.getAtPermissions())
//			{
//				System.out.println(atPermission.getDesc());
//			}
//		}
//		
//	}

}
