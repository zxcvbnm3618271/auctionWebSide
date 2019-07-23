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

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.daoimpl.AuctionDAOImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.StringUtil;


public class AuctionBIZImpl implements AuctionBIZ {
	
	AuctionDAO auctionDAO=new AuctionDAOImpl();
	
	 public List<Auction>auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum){
		//没有业务逻辑也要有业务层 以后发展会有
		 //分页 必须在页码-1
		 return auctionDAO.auctionListByPage(pageIndex.subtract(new BigDecimal(1)).multiply(pageNum), pageNum);
		 
	 }
	 public BigDecimal getAllcount(){
		return auctionDAO.getAllcount();
		 
	 }
	@Override
	public String auctionAdd(ServletConfig config, HttpServletRequest request,
			HttpServletResponse response) {

		
		// 实例化smartupload 实现上传下载
				SmartUpload smartUpload = new SmartUpload();
				String auctionState = null;
				try {

					// 初始化smartupload 这一步完成后 smartupload就知道用户上传的文件和用户提交的参数
					smartUpload.initialize(config, request, response);
					// 设置文件上传的大小
					smartUpload.setMaxFileSize(1024 * 1024 * 10);
					// 开启文件上传
					smartUpload.upload();
					// 获取文件
					File usefileFile = smartUpload.getFiles().getFile(0);
					String auctionName = smartUpload.getRequest().getParameter(
							"auctionName");
					String startPrice = smartUpload.getRequest().getParameter(
							"startPrice");
					String upset = smartUpload.getRequest().getParameter("upset");
					String startTime = smartUpload.getRequest().getParameter(
							"startTime");
					String endTime = smartUpload.getRequest().getParameter("endTime");
					String desc=smartUpload.getRequest().getParameter("desc");
					//实例化一个auction
					Auction auction=new Auction();
					//根据用户的传参来确认是否要实例化拍卖品的属性
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
					// 如果上传文件不为空
					if (usefileFile.getSize() > 0) {
						// 获取文件后缀名

						String fileEXE = usefileFile.getFileExt();
						// 生成文件名 文件名 使用时间戳
						String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
								.format(new Date());
						// 合成文件名
						fileName = fileName + "." + fileEXE;
						// 获取到服务器上upload文件夹的路径 用户上传的文件 可以有两种存储形式
						// 一种是存储到web容器中,还有一种是存储到数据库中(这种方式 适合小文件 好处是易于管理 坏处是性能消耗大).
						String hostPath = request.getSession().getServletContext()
								.getRealPath("upload");
						//将auction的picpath设置为生成的文件名
						auction.setAuctionPICPath(fileName);
						//吐过添加失败 下面的代码不会执行 因为抛出的异常被BIZ层获取了 下面的代码不会执行
						//目的是为了控制整个业务的原子性
						int resultCount= auctionDAO.auctionAdd(auction);
						if (resultCount>0) {
							// 存储文件
							// 为什么使用File.separator而不是使用/ 因为File.separator具有跨平台性
							usefileFile
									.saveAs(hostPath + java.io.File.separator + fileName);
						auctionState= AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue();
						}
						
					} else {
						//用户没有上传文件 进行拍卖品的添加
						int resultCount=auctionDAO.auctionAdd(auction);
						if (resultCount>0) {
							auctionState=AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue();
						}else {
							auctionState=AuctionStateEnum.AUCTION_ADD_FAIL.getValue();
						}
						
					}

				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		// 实例化smartupload 实现上传下载
		SmartUpload smartUpload = new SmartUpload();
		String auctionState = AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
		try {

			// 初始化smartupload 这一步完成后 smartupload就知道用户上传的文件和用户提交的参数
			smartUpload.initialize(config, request, response);
			// 设置文件上传的大小
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 开启文件上传
			smartUpload.upload();
			
			
			// 获取文件
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
			//实例化一个auction
			String auctionFile= smartUpload.getRequest().getParameter("pic02");
			Auction auction=new Auction();
			auction.setAuctionID(Integer.parseInt(auctionId));
		////	//根据用户的传参来确认是否要实例化拍卖品的属性
			
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
			// 如果上传文件不为空
			if (usefileFile.getSize() > 0) {
				// 获取文件后缀名

				String fileEXE = usefileFile.getFileExt();
				// 生成文件名 文件名 使用时间戳
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date());
				// 合成文件名
				fileName = fileName + "." + fileEXE;
				// 获取到服务器上upload文件夹的路径 用户上传的文件 可以有两种存储形式
				// 一种是存储到web容器中,还有一种是存储到数据库中(这种方式 适合小文件 好处是易于管理 坏处是性能消耗大).
				String hostPath = request.getSession().getServletContext()
						.getRealPath("upload");
				//将auction的picpath设置为生成的文件名
				auction.setAuctionPICPath(fileName);
				//吐过添加失败 下面的代码不会执行 因为抛出的异常被BIZ层获取了 下面的代码不会执行
				//目的是为了控制整个业务的原子性
				int resultCount= auctionDAO.auctionUpdate(auction);
				if (resultCount>0) {
					
					
					java.io.File file=new java.io.File(hostPath + java.io.File.separator + auctionFile);
					
					// 存储文件
					file.delete();
					// 为什么使用File.separator而不是使用/ 因为File.separator具有跨平台性
					usefileFile
							.saveAs(hostPath + java.io.File.separator + fileName);
				auctionState= AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				}else {
					auctionState= AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
				}
				
			} else {
				
				auction.setAuctionPICPath(auctionFile);
				
				//用户没有上传文件 进行拍卖品的添加
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
