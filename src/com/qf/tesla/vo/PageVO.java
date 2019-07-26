package com.qf.tesla.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class PageVO<T> implements Serializable {
	private List<T> lists;
	private BigDecimal pageIndex;  //当前页数
	private BigDecimal pageNum;    //每页数量
	private BigDecimal total;      //总页数
	private BigDecimal endPage;    //最后一页

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
	

	public BigDecimal getPageNum() {
		return pageNum;
	}

	public void setPageNum(BigDecimal pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "PageVO [pageIndex=" + pageIndex + ", pageNum=" + pageNum
				+ ", total=" + total + ", endPage=" + endPage + "]";
	}

}
