package com.qianfeng.bizimpl;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.daoimpl.AuctionDAOImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class AuctionBIZImpl implements AuctionBIZ {
	
	AuctionDAO auctionDAO;
	
	 public List<Auction>auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum){
		//娌℃湁涓氬姟閫昏緫涔熻鏈変笟鍔″眰 浠ュ悗鍙戝睍浼氭湁
		 //鍒嗛〉 蹇呴』鍦ㄩ〉鐮�1
		 return auctionDAO.auctionListByPage(pageIndex.subtract(new BigDecimal(1)).multiply(pageNum), pageNum);
		 
	 }
	 public BigDecimal getAllcount(){
		return auctionDAO.getAllcount();
		 
	 }
	 
	 
	public AuctionDAO getAuctionDAO() {
		return auctionDAO;
	}
	public void setAuctionDAO(AuctionDAO auctionDAO) {
		this.auctionDAO = auctionDAO;
	}
	@Override
	public String auctionAdd(java.io.File userFile,String fileName,String hostPath,Auction auction){

		 String auctionState=AuctionStateEnum.AUCTION_ADD_FAIL.getValue();
		try {
			if (userFile!=null) {
				fileName=new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date())
				+fileName.substring(fileName.indexOf("."),
						fileName.length());
				auction.setAuctionPICPath(fileName);
				auctionDAO.auctionAdd(auction);
				FileUtils.copyFile(userFile, new java.io.File(hostPath
						+java.io.File.separator+fileName));
				auctionState=AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue();
			}else {
				auctionDAO.auctionAdd(auction);
				auctionState=AuctionStateEnum.AUCTION_ADD_FAIL.getValue();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				return auctionState;
	}
	
	
	
	
	@Override
	public Auction auctionFindById(int auctionId) {
		// TODO Auto-generated method stub
		
		return auctionDAO.auctionFindById(auctionId);
	}
	
	
	@Override
	public int auctionDelByID(int auctionID) {
		// TODO Auto-generated method stub
		return auctionDAO.auctionDelByID(auctionID);
	}
	@Override
	public List<Auction> serchEndAuctionList() {
		// TODO Auto-generated method stub
		return auctionDAO.serchEndAuctionList();
	}
	@Override
	public List<Auction> serchNotEndAuctionList() {
		// TODO Auto-generated method stub
		return auctionDAO.serchNotEndAuctionList();
	}
	@Override
	public List<Auction> searchAuctionList(String auctionName,
			String auctionStartTime, String auctionEndTime,
			String auctionStartPrice) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder("from Auction as at where 1=1");
		Auction auction=new Auction();
		if (auctionName!=null&&!auctionName.equals("")) {
			hql.append("and at.auctionName like :auctionName ");
			auction.setAuctionName(auctionName + "%");
			
		}
		if (auctionStartTime!=null&&!auctionStartTime.equals("")) {
			hql.append("and at.auctionStartTime >= :auctionStartTime ");
			auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
			
		}
		if (auctionEndTime!=null&&!auctionEndTime.equals("")) {
			hql.append("and at.auctionEndTime <= :auctionEndTime ");
			auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
			
		}
		if (auctionStartPrice!=null&&!auctionStartPrice.equals("")) {
			hql.append("and at.auctionStartPrice >= :auctionStartPrice ");
			auction.setAuctionStartPrice(Double.valueOf(auctionStartPrice));
			
		}
		return auctionDAO.searchAuctionList(hql.toString(),auction);
		
		
	}
	@Override
	public String auctionUpdate(java.io.File userFile, String fileName,
			String hostPath, Auction auction, String beforeFileName) {
		 String auctionState=AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
			try {
				if (userFile!=null) {
					fileName=new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date())
					+fileName.substring(fileName.indexOf("."),
							fileName.length());
					auction.setAuctionPICPath(fileName);
					auctionDAO.auctionUpdate(auction);
					
					java.io.File file=new java.io.File(hostPath
							+java.io.File.separator+beforeFileName);
					file.delete();
					
					FileUtils.copyFile(userFile, new java.io.File(hostPath
							+java.io.File.separator+fileName));
					auctionState=AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				}else {
					auction.setAuctionPICPath(beforeFileName);
					auctionDAO.auctionUpdate(auction);
					auctionState=AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
					return auctionState;
	}
	

		
	
}
