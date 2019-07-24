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
	
	AuctionDAO auctionDAO=new AuctionDAOImpl();
	
	 public List<Auction>auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum){
		//娌℃湁涓氬姟閫昏緫涔熻鏈変笟鍔″眰 浠ュ悗鍙戝睍浼氭湁
		 //鍒嗛〉 蹇呴』鍦ㄩ〉鐮�1
		 return auctionDAO.auctionListByPage(pageIndex.subtract(new BigDecimal(1)).multiply(pageNum), pageNum);
		 
	 }
	 public BigDecimal getAllcount(){
		return auctionDAO.getAllcount();
		 
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
				auctionState=AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue();
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
	public String auctionUpdate(ServletConfig config,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 瀹炰緥鍖杝martupload 瀹炵幇涓婁紶涓嬭浇
		SmartUpload smartUpload = new SmartUpload();
		String auctionState = AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
		try {

			// 鍒濆鍖杝martupload 杩欎竴姝ュ畬鎴愬悗 smartupload灏辩煡閬撶敤鎴蜂笂浼犵殑鏂囦欢鍜岀敤鎴锋彁浜ょ殑鍙傛暟
			smartUpload.initialize(config, request, response);
			// 璁剧疆鏂囦欢涓婁紶鐨勫ぇ灏�
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 寮�惎鏂囦欢涓婁紶
			smartUpload.upload();
			
			
			// 鑾峰彇鏂囦欢
			File usefileFile = smartUpload.getFiles().getFile(0);
			
			String auctionId= smartUpload.getRequest().getParameter(
					"auctionId");
			
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String startPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String upset = smartUpload.getRequest().getParameter("upset");
			String startTime = smartUpload.getRequest().getParameter(
					"startTime");
			String endTime = smartUpload.getRequest().getParameter("endTime");
			String desc=smartUpload.getRequest().getParameter("desc");
			//瀹炰緥鍖栦竴涓猘uction
			String auctionFile= smartUpload.getRequest().getParameter("pic02");
			Auction auction=new Auction();
			auction.setAuctionID(Integer.parseInt(auctionId));
		////	//鏍规嵁鐢ㄦ埛鐨勪紶鍙傛潵纭鏄惁瑕佸疄渚嬪寲鎷嶅崠鍝佺殑灞炴�
			
			if (StringUtil.notEmpty(auctionName)) {
				auction.setAuctionName(auctionName);
			}
			if (StringUtil.notEmpty(startPrice)) {
				auction.setAuctionStartPrice(Double.valueOf(startPrice));
			}
			if (StringUtil.notEmpty(upset)) {
				auction.setAuctionUpset(Double.valueOf(upset));
			}
			if (StringUtil.notEmpty(startTime)) {
				auction.setAuctionStartTime(Timestamp.valueOf(startTime));
			}
			if (StringUtil.notEmpty(endTime)) {
				auction.setAuctionEndTime(Timestamp.valueOf(endTime));
			}
			if (StringUtil.notEmpty(desc)) {
				auction.setAuctionDESC(desc);
			}
			auction.setCreateTime(new Timestamp(System.currentTimeMillis()));
			auction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			// 濡傛灉涓婁紶鏂囦欢涓嶄负绌�
			if (usefileFile.getSize() > 0) {
				// 鑾峰彇鏂囦欢鍚庣紑鍚�

				String fileEXE = usefileFile.getFileExt();
				// 鐢熸垚鏂囦欢鍚�鏂囦欢鍚�浣跨敤鏃堕棿鎴�
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date());
				// 鍚堟垚鏂囦欢鍚�
				fileName = fileName + "." + fileEXE;
				// 鑾峰彇鍒版湇鍔″櫒涓妘pload鏂囦欢澶圭殑璺緞 鐢ㄦ埛涓婁紶鐨勬枃浠�鍙互鏈変袱绉嶅瓨鍌ㄥ舰寮�
				// 涓�鏄瓨鍌ㄥ埌web瀹瑰櫒涓�杩樻湁涓�鏄瓨鍌ㄥ埌鏁版嵁搴撲腑(杩欑鏂瑰紡 閫傚悎灏忔枃浠�濂藉鏄槗浜庣鐞�鍧忓鏄�鑳芥秷鑰楀ぇ).
				String hostPath = request.getSession().getServletContext()
						.getRealPath("upload");
				//灏哸uction鐨刾icpath璁剧疆涓虹敓鎴愮殑鏂囦欢鍚�
				auction.setAuctionPICPath(fileName);
				//鍚愯繃娣诲姞澶辫触 涓嬮潰鐨勪唬鐮佷笉浼氭墽琛�鍥犱负鎶涘嚭鐨勫紓甯歌BIZ灞傝幏鍙栦簡 涓嬮潰鐨勪唬鐮佷笉浼氭墽琛�
				//鐩殑鏄负浜嗘帶鍒舵暣涓笟鍔＄殑鍘熷瓙鎬�
				int resultCount= auctionDAO.auctionUpdate(auction);
				if (resultCount>0) {
					
					
					java.io.File file=new java.io.File(hostPath + java.io.File.separator + auctionFile);
					
					// 瀛樺偍鏂囦欢
					file.delete();
					// 涓轰粈涔堜娇鐢‵ile.separator鑰屼笉鏄娇鐢� 鍥犱负File.separator鍏锋湁璺ㄥ钩鍙版�
					usefileFile
							.saveAs(hostPath + java.io.File.separator + fileName);
				auctionState= AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				}else {
					auctionState= AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
				}
				
			} else {
				
				auction.setAuctionPICPath(auctionFile);
				
				//鐢ㄦ埛娌℃湁涓婁紶鏂囦欢 杩涜鎷嶅崠鍝佺殑娣诲姞
				int resultCount=auctionDAO.auctionUpdate(auction);
				if (resultCount>0) {
					auctionState=AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				}else {
					auctionState=AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
				}
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return auctionState;
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

		
	
}
