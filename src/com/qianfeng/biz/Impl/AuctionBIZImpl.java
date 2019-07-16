package com.qianfeng.biz.Impl;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.biz.AuctionBIZ;
import com.qianfeng.dao.AuctionDAO;
import com.qianfeng.dao.Impl.AuctionDAOImpl;
import com.qianfeng.entity.Auction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctionBIZImpl implements AuctionBIZ {

	AuctionDAO auctionDAO=new AuctionDAOImpl();
	
	@Override
	public List<Auction> auctionListByPage(BigDecimal pageIndex,
			BigDecimal pageNum) {
		// TODO Auto-generated method stub
		//哪怕业务逻辑层没有对应的业务逻辑 也不要没有业务层
		//分页的定律 必须在页码的基础上-1
		//limit 分页 10(从第10条开始),5(在第10条的基础山向前推进5条数据)
		return auctionDAO.AuctionListByPage(pageIndex.subtract(new BigDecimal(1)).multiply(pageNum), pageNum);
	}

	@Override
	public BigDecimal getAllCount() {
		// TODO Auto-generated method stub
		return auctionDAO.getAllCount();
	}

}
