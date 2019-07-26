package com.qf.tesla.enums;

import org.omg.CORBA.PRIVATE_MEMBER;

public enum AuctionLoginStateEnum {
	// 命名纯大写，每个单词用下划线隔开
	AUCTION_lOGIN_SUCCESS("login_success", "登入成功"), AUCTION_lOGIN_FAIL(
			"login_fail", "登入失败"), AUCTION_lOGIN_VALIDATACOMDE_ERROR(
			"validata_code_error", "验证码错误");

	private String value;
	private String desc;

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

}
