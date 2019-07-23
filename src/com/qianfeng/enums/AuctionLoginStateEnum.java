package com.qianfeng.enums;

public enum AuctionLoginStateEnum {
	AUCTION_LOGIN_SUCCESS("login_success","登录成功"),
	AUCTION_LOGIN_FAIL("login_fail","登录失败用户或密码错误"),
	AUCTION_LOGIN_VALIDATACODE_ERROR("validata_code_error","验证码错误");
	
	private String value;
	private String desc;
	
	private AuctionLoginStateEnum(String value,String desc){
		this.value=value;
		this.desc=desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
