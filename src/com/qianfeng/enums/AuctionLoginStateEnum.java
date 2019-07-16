package com.qianfeng.enums;

public enum AuctionLoginStateEnum {

	AUCTION_LOGIN_SUCCESS("loin_success", "登陆成功"), AUCTION_LOGIN_FAIL(
			"login_fail", "登录失败用户或密码错误"), AUCTION_LOGIN_VALIDATECODE_ERROR(
			"validate_code_error", "验证码错误");

	private AuctionLoginStateEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
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

	private String value;
	private String desc;

}
