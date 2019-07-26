package com.qf.tesla.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qf.tesla.biz.AuctionBIZ;
import com.qf.tesla.biz.AuctionUserBIZ;
import com.qf.tesla.bizimpl.AuctionBIZImpl;
import com.qf.tesla.bizimpl.AuctionUserBIZImpl;
import com.qf.tesla.dao.AuctionUserDao;
import com.qf.tesla.entity.AuctionUser;
import com.qf.tesla.enums.AuctionLoginStateEnum;
import com.qf.tesla.enums.AuctionStateEnum;
import com.qf.tesla.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AuctioinUserAction extends ActionSupport implements
		ModelDriven<AuctionUser> {
	private AuctionUser auctionUser = new AuctionUser("defaultUserName",
			"defaultPwd");
	AuctionUserBIZ auctionUserBIZ;
	private String userInputcode = "";
	private String sysCode;
	private String msg;
	String remeberMe;

	// 全局的，所有方法都会先进入到这里
	@Override
	public void validate() {// action级别的校验

	}

	public void validateLogin() {
		// 为函数指定拦截，进行校验，命名规则符合validateXxx,即可
		if (StringUtil.isEmpty(auctionUser.getUserName())
				|| auctionUser.getUserName().length() < 3) {
			// 一旦不合法返回到input对应的页面
			addFieldError("usNameMsg", "用户名不能为空且大于三位");
		}
		if (StringUtil.isEmpty(auctionUser.getUserPassWord())
				|| auctionUser.getUserPassWord().length() < 3) {
			addFieldError("pwdMsg", "用户密码不能为空且大于2位");
		}
	}

	public String login() {
		Subject subject = SecurityUtils.getSubject();
		// 自动登入
		if (subject.isRemembered()) {
			msg = AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS.getValue();
			return SUCCESS;
		} else {
			String sysCode = (String) ServletActionContext.getRequest()
					.getSession().getAttribute("numrand");
			if (StringUtil.isEmpty(userInputcode)
					|| StringUtil.isEmpty(sysCode)) {
				msg = AuctionLoginStateEnum.AUCTION_lOGIN_FAIL.getValue();
				return INPUT;
			}
			if (!userInputcode.equals(sysCode)) {
				msg = AuctionLoginStateEnum.AUCTION_lOGIN_VALIDATACOMDE_ERROR
						.getValue();
				return INPUT;
			}
			// 验证码无误，判断用户是否下次自动登入
			UsernamePasswordToken token = null;
			if (StringUtil.notEmpty(remeberMe)) {
				token = new UsernamePasswordToken(auctionUser.getUserName(),
						auctionUser.getUserPassWord(), true);
				
			}else {
				token = new UsernamePasswordToken(auctionUser.getUserName(), auctionUser.getUserPassWord());
			}
			
			try {
				subject.login(token);
				msg = AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS.getValue();
				AuctionUser auctionUser1 = new AuctionUser();
				auctionUser1 = auctionUserBIZ.findAuctionUserByName(auctionUser
						.getUserName());
				ActionContext.getContext().getSession()
						.put("user", auctionUser1);
				return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return INPUT;
		}

	}

	/*
	 * public static void main(String[] args) { // md5做一次散列后使用用户名作为盐值 String h1
	 * = new Md5Hash("123", "weng", 1).toString(); String h2 = new
	 * Md5Hash("123", "www", 1).toString();
	 * 
	 * System.out.println("weng:"+h1); System.out.println("www:"+h2); }
	 */

	public String logout() {
//		ServletActionContext.getRequest().getSession().invalidate();
		SecurityUtils.getSubject().logout();
		return SUCCESS;
	}

	// 获取控制层绑定的模型
	@Override
	public AuctionUser getModel() {
		// TODO Auto-generated method stub
		return auctionUser;
	}

	// get用来响应用户的数据
	public AuctionUser getAuctionUser() {
		return auctionUser;
	}

	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}

	public String getUserInputcode() {
		return userInputcode;
	}

	public void setUserInputcode(String userInputcode) {
		this.userInputcode = userInputcode;
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

	public String getRemeberMe() {
		return remeberMe;
	}

	public void setRemeberMe(String remeberMe) {
		this.remeberMe = remeberMe;
	}

}
