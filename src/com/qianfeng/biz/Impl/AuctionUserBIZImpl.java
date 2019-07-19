package com.qianfeng.biz.Impl;

import javax.servlet.http.HttpServletRequest;

import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.dao.AuctionUserDAO;
import com.qianfeng.dao.Impl.AuctionUserDAOImpl;
import com.qianfeng.entity.AuctionUser;
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.util.MD5;
import com.qianfeng.util.StringUtil;

public class AuctionUserBIZImpl implements AuctionUserBIZ {

	@Override
	public String auctionUserLogin(String userName, String passWord,
			String userInputCode, String sysCode,HttpServletRequest request) {
		try {
			// TODO Auto-generated method stub
			if (StringUtil.isEmpty(userInputCode) || StringUtil.isEmpty(sysCode)) {
				return AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATECODE_ERROR
						.getValue();
			}

			if (!userInputCode.equals(sysCode)) {
				return AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATECODE_ERROR
						.getValue();
			}
			// 如果上面的场景全部满足 判断数据库是否能匹配
			AuctionUserDAO auctionUserDAO = new AuctionUserDAOImpl();
			// 一般密码使用MD5加密 散列次数越高 越难破解
			AuctionUser auctionUser = auctionUserDAO.auctionLogin(userName,
					MD5.MD5(passWord));
			if (auctionUser == null) {
				return AuctionLoginStateEnum.AUCTION_LOGIN_FAIL.getValue();
			}
			request.getSession().setAttribute("user", auctionUser);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return AuctionLoginStateEnum.AUCTION_LOGIN_SUCCESS.getValue();
		}
	}

}
