package com.qf.tesla.bizimpl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.Msg;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jspsmart.upload.SmartUpload;
import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.dao.AuctionDao;
import com.qf.tesla.daoimpl.AuctionDaoImpl;
import com.qf.tesla.entity.Auction;
import com.qf.tesla.enums.AuctionStateEnum;
import com.qf.tesla.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Entity
public class AuctionBIZImpl implements AuctionBIZ {

	@ManyToOne
	AuctionDao auctionDao ;
	


	@Override
	public List<Auction> auctonListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		// TODO Auto-generated method stub
		return auctionDao.auctonListByPage(pageIndex
				.subtract(new BigDecimal(1)).multiply(pageNum), pageNum);
	}

	@Override
	public BigDecimal getAllcount() {
		// TODO Auto-generated method stub
		return auctionDao.getAllcount();
	}

	@Override
	public String auctionAdd(File userFile, String fileName, String hostPath,
			Auction auction) {
		// TODO Auto-generated method stub
		String auctionState = AuctionStateEnum.AUCTION_ADD_FAIL.getVaule();
		try {
			if (userFile != null) {
				fileName = new SimpleDateFormat("yyyyMMddHHmmssSS")
						.format(new Date())
						+ fileName.substring(fileName.indexOf("."),
								fileName.length());
				auction.setAuctionPICPath(fileName);
				auctionDao.auctionAdd(auction);
				FileUtils.copyFile(userFile, new File(hostPath + File.separator
						+ fileName));
				auctionState = AuctionStateEnum.AUCTION_ADD_SUCCESS.getVaule();
			} else {
				auctionDao.auctionAdd(auction);
				auctionState = AuctionStateEnum.AUCTION_ADD_SUCCESS.getVaule();
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
		return auctionDao.auctionFindById(auctionId);
	}

	@Override
	public String auctionUpdate(File userFile, String fileName,
			String hostPath, Auction auction, String beforeFileName) {

		String auctionState = AuctionStateEnum.AUCTION_ADD_FAIL.getVaule();
		try {
			if (userFile != null) {
				fileName = new SimpleDateFormat("yyyyMMddHHmmssSS")
						.format(new Date())
						+ fileName.substring(fileName.indexOf("."),
								fileName.length());
				auction.setAuctionPICPath(fileName);
				auctionDao.auctionUpdate(auction);
				File file = new File(hostPath + File.separator + beforeFileName);
				file.delete();

				FileUtils.copyFile(userFile, new File(hostPath + File.separator
						+ fileName));

				auctionState = AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getVaule();
			} else {
				auction.setAuctionPICPath(beforeFileName);
				auctionDao.auctionUpdate(auction);
				auctionState = AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getVaule();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auctionState;
	}

	@Override
	public int deleteById(int auctionId) {
		// TODO Auto-generated method stub
		return auctionDao.deleteById(auctionId);
	}

	@Override
	public List<Auction> searchNotEndAuctionList() {
		// TODO Auto-generated method stub

		return auctionDao.searchNotEndAuctionList();
	}

	@Override
	public List<Auction> searchEndAuctionList() {
		// TODO Auto-generated method stub
		return auctionDao.searchEndAuctionList();
	}
	
	public AuctionDao getAuctionDao() {
		return auctionDao;
	}

	public void setAuctionDao(AuctionDao auctionDao) {
		this.auctionDao = auctionDao;
	}


	@Override
	public List<Auction> searchAuctionList(String auctionName,
			String auctionStartTime, String auctionEndTime,
			String auctionStratPrice) {
		// TODO Auto-generated method stub
		// 注意事项：1.要有恒等条件方便拼接，2.写完字符串后最好在后面打一个空格，因为sql语句不能黏在一起
		// 非数字类型必须要加单引号,因为这是直接拼接
		Auction auction = new Auction();

//		AuctionDao auctionDao = new AuctionDaoImpl();

		StringBuilder hqlBuilder = new StringBuilder("from Auction where 1=1 ");
		if (StringUtil.notEmpty(auctionName)) {
			hqlBuilder.append("and AUCTIONNAME like :auctionName ");
			auction.setAuctionName(auctionName + "%");
		}
		if (StringUtil.notEmpty(auctionStartTime)) {
			hqlBuilder.append("and AUCTIONSTARTTIME >= :auctionStartTime ");
			auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
		}
		if (StringUtil.notEmpty(auctionEndTime)) {
			hqlBuilder.append("and AUCTIONENDTIME >=  :auctionEndTime ");
			auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
		}
		if (StringUtil.notEmpty(auctionStratPrice)) {
			hqlBuilder.append("and AUCTIONSTARTPRICE <= :auctionStartPrice ");
			auction.setAuctionStartPrice(Double.valueOf(auctionStratPrice));
		}

		return auctionDao.searchAuctionList(hqlBuilder.toString(), auction);
	}

}
