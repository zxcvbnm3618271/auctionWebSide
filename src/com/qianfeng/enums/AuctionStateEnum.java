package com.qianfeng.enums;

public enum AuctionStateEnum {

	AUCTION_ADD_SUCCESS("addsucces","添加成功"),
	AUCTION_ADD_FAIL("addfail","添加失败"),
	AUCTION_UPDATE_SUCCESS("updatesucces","修改成功"),
	AUCTION_UPDATE_FAIL("updatefail","修改失败"),
	AUCTION_DELETE_SUCCESS("deletesucces","删除成功"),
	AUCTION_DELETE_FAIL("deletefail","删除失败");
	
	private String value;
	private String desc;
	
	private AuctionStateEnum(String value,String desc){
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
