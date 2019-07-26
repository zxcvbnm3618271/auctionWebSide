package com.qianfeng.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qianfeng.biz.AuctionUserBIZ;
import com.qianfeng.bizimpl.AuctionUserBIZImpl;
import com.qianfeng.entity.AuctionUser;  
import com.qianfeng.enums.AuctionLoginStateEnum;
import com.qianfeng.util.StringUtil;

public class AuctionUserAction extends ActionSupport implements
                 ModelDriven<AuctionUser> {

	private AuctionUser auctionUser=new AuctionUser();
	private String userInputCode;
	private String sysCode;
	private String msg;
	AuctionUserBIZ auctionUserBIZ ;
	

	//struts2 validate 拦截器提供合法数据校验功能
	
	
	@Override
	public void validate(){
		if (StringUtil.isEmpty( auctionUser.getUserName())||auctionUser.getUserName().length()<5) {
			addFieldError("usNameMsg", "用户名不能为空且大于3位");
		}
		if (StringUtil.isEmpty( auctionUser.getUserPassWord())||auctionUser.getUserPassWord().length()<3) {
			addFieldError("pwdMsg", "用户密码不能为空且大于2位");
		}
	}
	
	
	public String login() {
		msg = auctionUserBIZ.auctionUserLogin(getModel().getUserName(),
				getModel().getUserPassWord(), userInputCode,
				(String) ServletActionContext.getRequest().getSession()
						.getAttribute("numrand"),
				ServletActionContext.getRequest());
		if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_VALIDATACODE_ERROR.getValue())) {
			return INPUT;
		}else if (msg.equals(AuctionLoginStateEnum.AUCTION_LOGIN_FAIL.getValue())) {
			return INPUT;
		}else {
			return SUCCESS;
		}
		
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return SUCCESS;
	}

	@Override
	public AuctionUser getModel() {
		// TODO Auto-generated method stub
		return auctionUser;
	}

	public AuctionUser getAuctionUser() {
		return auctionUser;
	}

	public String getUserInputCode() {
		return userInputCode;
	}

	public void setUserInputCode(String userInputCode) {
		this.userInputCode = userInputCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AuctionUserBIZ getAuctionUserBIZ() {
		return auctionUserBIZ;
	}

	public void setAuctionUserBIZ(AuctionUserBIZ auctionUserBIZ) {
		this.auctionUserBIZ = auctionUserBIZ;
	}

	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}

}
