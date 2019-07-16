package com.qianfeng.biz;

import java.math.BigDecimal;
import java.util.List;

import com.qianfeng.entity.Auction;


public interface AuctionBIZ {
/**
 * @Descript
 * @param pageIndex
 * @param pageNum
 * @return
 */
	List<Auction> auctionListByPage(BigDecimal pageIndex,BigDecimal pageNum);	
	/**
	 * 
	 * @return
	 */
	
	BigDecimal getAllCount();
}
