package com.qianfeng.biz.Impl;

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
import com.qianfeng.dao.Impl.AuctionDAOImpl;
import com.qianfeng.entity.Auction;
import com.qianfeng.enums.AuctionStateEnum;
import com.qianfeng.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionBIZImpl implements AuctionBIZ {

	AuctionDAO auctionDAO = new AuctionDAOImpl();

	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		// TODO Auto-generated method stub
		// 哪怕业务逻辑层没有对应的业务逻辑 也不要没有业务层
		// 分页的定律 必须在页码的基础上-1
		// limit 分页 10(从第10条开始),5(在第10条的基础山向前推进5条数据)
		return auctionDAO.AuctionListByPage(
				pageIndex.subtract(new BigDecimal(1)).multiply(pageNum),
				pageNum);
	}

	@Override
	public BigDecimal getAllCount() {
		// TODO Auto-generated method stub
		return auctionDAO.getAllCount();
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
			String desc = smartUpload.getRequest().getParameter("desc");
			// 实例化一个auction
			Auction auction = new Auction();
			// 根据用户的传参来确认是否要实例化拍卖品的属性
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
				// 将auction的picpath设置为生成的文件名
				auction.setAuctionPICPath(fileName);
				// 吐过添加失败 下面的代码不会执行 因为抛出的异常被BIZ层获取了 下面的代码不会执行
				// 目的是为了控制整个业务的原子性
				int resultCount = auctionDAO.auctionAdd(auction);
				if (resultCount > 0) {
					// 存储文件
					// 为什么使用File.separator而不是使用/ 因为File.separator具有跨平台性
					usefileFile.saveAs(hostPath + java.io.File.separator
							+ fileName);
					auctionState = AuctionStateEnum.AUCTION_ADD_SUCCESS
							.getValue();
				}

			} else {
				// 用户没有上传文件 进行拍卖品的添加
				int resultCount = auctionDAO.auctionAdd(auction);
				if (resultCount > 0) {
					auctionState = AuctionStateEnum.AUCTION_ADD_SUCCESS
							.getValue();
				} else {
					auctionState = AuctionStateEnum.AUCTION_ADD_FAIL.getValue();
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
		// 实例化smartupload 实现上传下载
		SmartUpload smartUpload = new SmartUpload();
		String auctionState = AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();

		try {
			// 初始化smartupload 这一步完成后 smartupload就知道用户上传的文件和用户提交的参数
			smartUpload.initialize(config, request, response);
			// 设置文件上传大小
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 开启上传
			smartUpload.upload();
			// 获取文件
			File usefileFile = smartUpload.getFiles().getFile(0);
			String auctionId = smartUpload.getRequest().getParameter(
					"auctionId");
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String startPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String upset = smartUpload.getRequest().getParameter("upset");
			String startTime = smartUpload.getRequest().getParameter(
					"startTime");
			String endTime = smartUpload.getRequest().getParameter("endTime");
			String desc = smartUpload.getRequest().getParameter("desc");
			// 获取到用户以前的文件名
			String auctionFile = smartUpload.getRequest().getParameter("pic02");

			// 实例化一个auction
			Auction auction = new Auction();
			if (StringUtil.notEmpty(auctionId)) {
				auction.setAuctionID(Integer.parseInt(auctionId));
			}
			// 根据用户的传参来确认是否要实例化拍卖品的属性
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

			// 如果上传文件名不为空
			if (usefileFile.getSize() > 0) {
				// 获取文件后缀名
				String fileEXE = usefileFile.getFileExt();
				// 获取到服务器上upload文件夹的路径 用户上传的文件 可以有两种存储形式
				// 一种是存储到web容器中,还有一种是存储到数据库中(这种方式 适合小文件 好处是易于管理 坏处是性能消耗大).
				String hostPath = request.getSession().getServletContext()
						.getRealPath("upload");
				// 生成文件名 文件名 使用时间戳
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date());
				// 合成文件名
				fileName = fileName + "." + fileEXE;
				// 将auction的picpath设置为生成的文件名
				auction.setAuctionPICPath(fileName);
				// 吐过添加失败 下面的代码不会执行 因为抛出的异常被BIZ层获取了 下面的代码不会执行
				// 目的是为了控制整个业务的原子性
				int resultCount = auctionDAO.auctionUpdate(auction);
				if (resultCount > 0) {

					java.io.File file = new java.io.File(hostPath
							+ java.io.File.separator + auctionFile);
					file.delete();
					// 存储文件
					// 为什么使用File.separator而不是使用/ 因为File.separator具有跨平台性
					usefileFile.saveAs(hostPath + java.io.File.separator
							+ fileName);
					auctionState = AuctionStateEnum.AUCTION_UPDATE_SUCCESS
							.getValue();
				} else {
					auctionState = AuctionStateEnum.AUCTION_UPDATE_FAIL
							.getValue();
				}

			} else {
				// 用户没有上传文件 进行拍卖品的添加

				auction.setAuctionPICPath(auctionFile);
				int resultCount = auctionDAO.auctionUpdate(auction);
				if (resultCount > 0) {
					auctionState = AuctionStateEnum.AUCTION_UPDATE_SUCCESS
							.getValue();
				} else {
					auctionState = AuctionStateEnum.AUCTION_UPDATE_FAIL
							.getValue();
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
	public int auctionDelByID(int auctionid) {
		// TODO Auto-generated method stub
		return auctionDAO.auctionDelByID(auctionid);
	}

	@Override
	public List<Auction> searchEndAuctionList() {
		// TODO Auto-generated method stub
		return auctionDAO.searchEndAuctionList();
	}

	@Override
	public List<Auction> searchNotEndAuctionList() {
		// TODO Auto-generated method stub
		return auctionDAO.searchNotEndAuctionList();
	}

	@Override
	public List<Auction> searchAuctionList(String auctionName,
			String auctionStartTime,String auctionEndTime, String auctionStartPrice) {
		// TODO Auto-generated method stub
		//非数字类型必须加单引号
				StringBuilder sql;
				sql = new StringBuilder("select * from auction where 1=1 ");
				try {
					
					if (StringUtil.notEmpty(auctionName)) {
						//写模糊查询 切记不要前模糊 会引起全表检索
						sql.append("and auctionname like '%"+auctionName+"%'");
					}
					if (StringUtil.notEmpty(auctionStartTime)) {
						sql.append("and auctionstarttime >= '"+Timestamp.valueOf(auctionStartTime) + "'");
						
					}
					if (StringUtil.notEmpty(auctionEndTime)) {
						sql.append("and auctionendtime <= '"+Timestamp.valueOf(auctionEndTime) + "'");
						
					}
					if (StringUtil.notEmpty(auctionStartPrice)) {
						sql.append("and auctionstartprice >= '"+Double.parseDouble(auctionStartPrice) + "'");
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return auctionDAO.searchAuctionList(sql.toString());
	}

}
