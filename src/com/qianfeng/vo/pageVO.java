package com.qianfeng.vo;

import java.math.BigDecimal;
import java.util.List;



public class pageVO<T> {
	//分页集合容器
	private List<T> lists;
	private BigDecimal pageIndex;
	private BigDecimal pageNum;
	private BigDecimal total;
	private BigDecimal endPage;
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public BigDecimal getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(BigDecimal pageIndex) {
		this.pageIndex = pageIndex;
	}
	public BigDecimal getPageNum() {
		return pageNum;
	}
	public void setPageNum(BigDecimal pageNum) {
		this.pageNum = pageNum;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getEndPage() {
		return endPage;
	}
	public void setEndPage(BigDecimal endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "pageVO [lists=" + lists + ", pageIndex=" + pageIndex
				+ ", pageNum=" + pageNum + ", total=" + total + ", endPage="
				+ endPage + "]";
	}
	
}
